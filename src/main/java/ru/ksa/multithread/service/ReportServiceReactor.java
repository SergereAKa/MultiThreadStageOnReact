package ru.ksa.multithread.service;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import ru.ksa.multithread.component.ReportStageReactor;
import ru.ksa.multithread.model.ProcessInfoReport;

import java.util.ArrayList;
import java.util.List;


@Service
@EnableAsync
@Log4j2
@Getter
public class ReportServiceReactor {
    private List<ReportStageReactor> stages = new ArrayList<>();

    /*****************************
     *
     * @return
     */
    public String generateReport() throws Exception{

        stages.forEach(s-> s.execute());
        //stages.clear();
        return "Report created";
    }

    public String shutdown(){
        stages.forEach(s->s.shutdown());
        return "Report shutdown";
    }

    public ProcessInfoReport getProcessReport() throws Exception{
        final ProcessInfoReport processInfoReport = new ProcessInfoReport();

        stages.forEach(s->s.loadProcessInfo(processInfoReport));

        return processInfoReport;
    }


}
