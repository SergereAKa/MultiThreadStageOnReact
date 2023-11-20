package ru.ksa.multithread.component;

import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import ru.ksa.multithread.model.ProcessInfoReport;
import ru.ksa.multithread.process.MTProcess;
import ru.ksa.multithread.process.reactor.ReactorProcess;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public class ReportStageReactor {
    private final List<ReactorProcess> processes;
    private AtomicBoolean stopFlag = new AtomicBoolean(false);
    private final Scheduler scheduler = Schedulers.newParallel("ReportStageReactor", 10);

    public ReportStageReactor(List<ReactorProcess> processes) {
        this.processes = processes;
    }

    public void shutdown(){
        scheduler.dispose();
        stopFlag.set(true);
    }

    public void execute()  {
        if(stopFlag.get()){
            return;
        }

        final ReactorProcess reactorProcess =  Flux.fromIterable(processes)
            .parallel(10)
            .runOn(scheduler)
            .flatMap(o-> Mono.fromCallable(()->{
                o.setStatus("Работает");
                o.setMessage("Запущен в работу");
                o.execute();
                o.setStatus("Отработал");
                o.setMessage("Работа закончена");
                return o;
            }))
            .sequential()
            .takeUntil(item->stopFlag.get())
            .blockLast();
    }
    /******************************************
     *
     * @param report
     */
    public void loadProcessInfo(ProcessInfoReport report){

        processes.forEach(p->{
            report.getProcesses().add(p.getProcessInfo());
        });
    }
}
