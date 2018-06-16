# AbstractQueuedSynchronizer - AQS  jdk1.5  

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
    
#### ReentrantLock (可重入锁) 和 synchronized 区别？

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
    
    ReentrantLock 是一种自解锁，通过循环调用CAS操作来实现加锁。
    性能比较好的原因是避免线程进入内核态的阻塞状态。
    
    ReentrantLock 这么好，是不是以后就不需要用到synchronized了呢？
    1、ReentrantLock 高级用户的高级特性，同步已经成为可伸缩性的瓶颈的时候。
    2、synchronized 不会忘记释放锁，jvm自动做。否则工作块出现死锁
    3、当jvm使用synchronized 管理锁定请求和释放时，jvm在生成线程转储时，能包括锁定信息，对调试有价值。
    能包含死锁和其他异常的来源。
    4、同事熟悉synchronized 、大部分场景用synchronized可以解决
    
### ReentrantLock 主要的方法？

    tryLock() 仅在调用锁定时，未被另一个线程保持的情况下，获取锁定。
    tryLock(long ，TimeUnit)  如果锁定在给定的时间内没有被另一个线程保持，且当前线程没有中断，则获取这个锁定
    lockInterruptibly() 如果当前线程没有被中断的话，则获取锁定，如果被中断了，则抛出异常
    isLocked()  查询此锁定是否由任意线程保持
    isHeldByCurrentThread() 当前线程是否保持锁定状态
    isFair()判断是否为公平锁
    
    hasQueuedThread(Thread ) 查询指定线程是否在等待获取此锁定
    hasQueuedThreads() 查询是否有线程在等待获取此锁定
    getHoldCount() 查询当前线程保持锁定的个数，也就是调用了lock()方法的个数
   
   
# ReentranReadWriteLock
    
    在没有任何读写锁的时候才可以取得写入锁。
    用于实现悲观读取，我们在执行中进行读取时同时需要执行写入操作。
    如果读取非常多，那么有可能造成获得写入锁造成饥饿（写入线程迟迟无法竞争到线程而处于等待状态）

### StampedLock

    三种状态： 写 、读 、乐观读
    重点乐观读：
    如果读的操作很多，写的操作很少，我们可以乐观的认为读取和写入同时发生的机率很小，因此并不完全悲观的使用
    程序可以查看读取资料之后，是否遭到写入执行的变更，在采取后续的措施。
    

### 关于锁的总结

    1、synchronized 是jvm层面的实现的，不但通过一些监控工具监控，而且在代码执行时出现异常，jvm也会自动释放锁定。
    jvm会自动实现加锁与解锁。
    2、ReentrantLock 、 ReentranReadWriteLock 、StampedLock 等等都是对象层面的锁定。必须要保证锁一定会释放( unlock操作必须放到finally里边，才更安全)
    3、StampedLock 对吞吐量有巨大改进，特别是在读线程越来越多的场景下。

### 关于锁的选择？
    1、当之后少量竞争者的时候 synchronized 是非常好的选择。
    2、竞争者不少，但是线程增长的曲线是可以预估的，这个是时候ReentrantLock最好的选择
    3、避免造成死锁

###Condition 组件
   
    多线程之间协调通讯的工具。
            





    

    

    
   
    
    

     

    