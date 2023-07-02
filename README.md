Running the main method in [src/main/java/com/example/App.java](src/main/java/com/example/App.java) throws an exception.

Based on my initial investigation, it appears as if Java's http client has issues with the fact that the response
header, which should be something like:<br/>
`'multipart/mixed; boundary="----=_Part_44_1172350058.1688280927725"`<br/>
Contains redundant characters like tabs and/or new lines that causes the exception.<br/>

Go's client was able to handle it, But Java's client can't.<br/>
The other two types we work with in this project, _application/json_ and _text/html_ obviously work well and are not
demonstrated in this example repo.

```
Exception in thread "main" java.io.IOException: protocol error: java.io.IOException: Bad header value 'multipart/mixed;
	boundary="----=_Part_44_1172350058.1688280927725"'
	at java.net.http/jdk.internal.net.http.HttpClientImpl.send(HttpClientImpl.java:857)
	at java.net.http/jdk.internal.net.http.HttpClientFacade.send(HttpClientFacade.java:123)
	at com.example.App.main(App.java:22)
Caused by: java.io.IOException: protocol error: java.io.IOException: Bad header value 'multipart/mixed;
	boundary="----=_Part_44_1172350058.1688280927725"'
	at java.net.http/jdk.internal.net.http.Http2Connection.protocolError(Http2Connection.java:1041)
	at java.net.http/jdk.internal.net.http.Http2Connection.processFrame(Http2Connection.java:835)
	at java.net.http/jdk.internal.net.http.frame.FramesDecoder.decode(FramesDecoder.java:155)
	at java.net.http/jdk.internal.net.http.Http2Connection$FramesController.processReceivedData(Http2Connection.java:232)
	at java.net.http/jdk.internal.net.http.Http2Connection.asyncReceive(Http2Connection.java:681)
	at java.net.http/jdk.internal.net.http.Http2Connection$Http2TubeSubscriber.processQueue(Http2Connection.java:1387)
	at java.net.http/jdk.internal.net.http.common.SequentialScheduler$LockingRestartableTask.run(SequentialScheduler.java:205)
	at java.net.http/jdk.internal.net.http.common.SequentialScheduler$CompleteRestartableTask.run(SequentialScheduler.java:149)
	at java.net.http/jdk.internal.net.http.common.SequentialScheduler$SchedulableTask.run(SequentialScheduler.java:230)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	at java.base/java.lang.Thread.run(Thread.java:1589)
```
