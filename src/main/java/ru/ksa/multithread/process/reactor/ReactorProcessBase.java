package ru.ksa.multithread.process.reactor;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;
import ru.ksa.multithread.model.ProcessInfo;

import java.util.Date;
@Getter
@Setter
@RequiredArgsConstructor
public class ReactorProcessBase implements ReactorProcess{
    private  final String command;
    private  final Date reportDate;
    private  final Date contractDate;
    private  final String contractType;
    private  final String comment;
    private  final int sleeplen;

    private  String status = "неопределен";
    private  Exception exception;
    private  String message = "Не в работе";


    @Override
    public void execute() {
        return ;
    }
    @Override
    public ProcessInfo getProcessInfo() {
        final ProcessInfo processInfo = new ProcessInfo();
        processInfo.setStatus(getStatus());
        processInfo.setComment(getComment());
        processInfo.setMesssage(getMessage());
        processInfo.setName(this.getClass().getName());
        return processInfo;
    }
}
