#AbstractQueuedSynchronizer - AQS  jdk1.5  

    JUC的重中之重，实现原理：
    
    1、提供了基于：First in First Out 队列  Queue 
    2、底层是双向列表 队列
    
    设计：
    1）使用Node实现FIFO队列，可以用于构建或者其他同步装置的基础框架。
    2）利用了一个int类型表示状态
    3）使用方法是继承
    4）子类通过继承并通过实现它的方法管理其状态 acquire 和 release
    5)可以同时实现排它锁和共享锁（独占、共享）

###AQS 组件：
 
    CountDownLatch  通过计数器控制线程是否阻塞
    Semaphore   控制同一时间并发的数量
    ReentrantLock  锁（阻塞进程）
    Condition  条件 
    FutureTask  
    
### CountDownLatch 闭锁
    
### Semaphore 信号量

### CyclicBarrier Cyclic(循环) Barrier(屏障)
    应用场景： 
    多线程计算数据，然后等线程都计算完成之后进行合并操作。
    
    CyclicBarrier 与 CountDownLatch 区别？相同点和不相同点
    
    相同点：
    1、都是通过计数器进行实现
    2、都是调用await方法之后进度等待状态
    
    不相同点：
    1、CountDownLatch 计数器只能使用一次 ，CyclicBarrier.reset()进行重置可以循环使用
    2、CountDownLatch 主要是一个线程或者多个线程需要等待其他线程操作完成之后才能继续往下执行。
       CyclicBarrier  主要是指多个线程相互等待，直到都满足条件之后继续往下执行。各个线程内部相互等待的过程。   
    3、CyclicBarrier 能处理更加复杂场景

    barrier.getNumberWaiting();  获得阻塞线程的数量 
    barrier.isBroken(); 阻塞的线程是否被中断了
    barrier.getParties(); 
    barrier.await();
    barrier.await(2000, TimeUnit.MILLISECONDS);
    barrier.reset() 

### ReentrantLock 与 锁

####ReentrantLock (可重入锁) 和 synchronized 区别？
    
    可重入性：
        一个线程进入一次，锁的计数器就自增一。所以等到锁的计数器下降为0时，释放锁。
        
    锁的实现：
        1、ReentrantLock 是jdk 实现，用代码可以控制
        2、synchronized 是jvm实现，是java虚拟机实现 
    性能：
        目前性能差不多
        synchronized 使用比较简单，所以官方推荐
    功能区别：
       1、ReentrantLock 有可能造成死锁(忘记 最好在finally方法中解锁)
       2、锁的细粒度和灵活性     ReentrantLock 相对较高一些
        
    ReentrantLock 独有功能？
    
    1、公平锁和非公平锁？
    公平锁：先等待的锁先获得锁执行。
    2、提供了Condition类，可以分组唤醒需要唤醒的线程
    3、提供了能够中断等待锁的线程机制，lock.lockInterruptibly()
    
    