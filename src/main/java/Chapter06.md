# 线程池

###new  Thread  弊端
    1) 每次new Thread 新建对象，性能差
    2) 线程缺乏统一管理，可能无限制的新建线程，相互竞争，有可能占用过的的系统资源导致死机
    或者OOM
    3) 缺少更过功能，如更多执行，定期执行，线程中断


###线程池的好处？

    1)重用存在的线程，减少对象创建、消亡的开销，性能佳
    2)有效控制最大并发线程数，提高系统资源利用率，同时可以避免过多资源竞争，避免阻塞
    3)提供定时执行、定期执行，单线程、并发数控制等功能


### TreadPoolExecutor

     参数最多的构造方法：
     new  TreadPoolExecutor (int corePoolSize,
     int maximumPoolSize,
     long keepAliveTime,
     TimeUnit unit,
     BlockingQueue<Runnable> workQueue,
     ThreadFactory threadFactory,
     RejectedExecutionHandler handler)  
 
    corePoolSize: 核心线程数量
    maximumPoolSize :线程最大线程数
    workQueue :阻塞队列，储存等待执行的任务，很重要，会对线程池运行过程产生重大影响
    (先看 corePoolSize ，再看workQueue ，再看 maximumPoolSize ) 
    任务处理三种方式:
     1、直接切换 SychronousQueue 
     2、使用无界队列  一版是使用基于列表的LinkedBlockingQueue队列 ，线程中创建的最大线程数为corePoolSize
     则maximumPoolSize 没有作用。
     当线程池中所有的核心线程都处于运行状态，此时一个任务提交，进入等待队列。
     3、使用有界队列
       ArrayBlockingQueue ，将线程池的线程数限制为maximumPoolSize，这样能降低线程池的消耗。
       
    keepAliveTime :线程没有任何任务执行时保持多久时间终止
    unit ： keepAliveTime 的时间单位
    threadFactory :线程工厂，用来创建线程
    rejectHandler:当拒绝处理任务时的策略
   
    四种策略：
    1）抛出异常  AbortPolicy
    2）使用调用者的线程来执行任务  CallerRunsPolicy
    3）丢弃队列中最靠前的任务，并执行当前的任务 DiscardOldestPolicy
    4）直接丢弃这个任务   DiscardPolicy

 
### 线程池的几种状态

初始化线程池之后的状态：

Running(运行状态): 能接收新的任务，也能处理阻塞队列中的任务
Shutdown(关闭状态):不能接收新提交的任务，但是能处理阻塞队列中已经保存的任务
Stop :不能接收新提交的任务，也不处理阻塞队列中的任务。他会中断正在处理任务的线程
Tidying(整理): 队列状态为空，线程池中工作的数量为0
Terminated(终止): 

### 线程池的方法

    execute()： 提交任务，交给线程池执行
    submit() :  提交任务，能够返回执行结果， execute + Future
    shutdown(): 关闭线程池，等待任务都执行完
    shutdownNow(): 关闭线程池，不等待任务执行完，暂定正在执行的线程
    
    适用于监控的方法:
    
    getTaskCount():线程池已执行和未执行的任务总数
    getCompletedTaskCount():已完成的任务数
    getPoolSize():线程池当前的线程数量
    getActiveCount(): 当前线程池中正在执行任务的数量


### Executor 框架
   Executor框架是一个根据一组执行策略的调用、调度、执行和控制的异步任务的框架。
   目的是提供一种：将任务提交以及任务如何运行分离开来的机制。
   
三个Executor接口：
Executor： 运行新任务的简单接口
ExecutorService：  扩展了Executor接口，添加了管理执行器和任务生命周期的方法。
ScheduleExecutorService :扩展了ExecutorService接口,他支持Future和定期执行任务。



### Executors 线程池

Executors.newCachedThreadPool()
创建一个可以缓存的线程池

Executors.newFixedThreadPool()
创建一个定长的线程池，超出的线程数在队列中等待

Executors.newScheduledThreadPool()
创建一个定长的线程池，它支持定时和周期性的任务执行

Executors.newSingleThreadExecutor()
创建一个单线程化的线程池，他仅仅只会用一个线程执行任务，保证所有任务按照指定顺序去执行(可以指定先入先出和优先级)



 
