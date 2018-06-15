并发测试工具有哪一些？

PostMan : Http请求模拟工具

Apache Bench (AB) :Apache附带的工具，测试网站性能 （小巧灵活、命令行操作）

JMeter : Apache组织开发的压力测试工具 (图形界面话操作)
(这个仔细的去学习一下)

#补充知识点

    注意，为了获得较好的执行性能，Java内存模型并没有限制执行引擎使用处理器的寄存器或者高速缓存来提升指令执行速度，也没有限制编译器对指令进行重排序。也就是说，在java内存模型中，也会存在缓存一致性问题和指令重排序的问题。

### Semaphore 信号

### CountDownLatch  闭锁

# 线程安全性？

    当多个线程访问某一个类，不管运行时环境采用何种调度方式，或者这些进程将如何交替执行，并且在主调代码中不需要任何额外的同步
    或协同，这个类都能够表现出正确的行为。那么这个类就是线程安全的类。
    
    原子性：提供了互斥访问，同一时刻只能有一个线程来对它进行操作。 
    可见性：一个线程对主内存的修改可以及时的被其他线程观察到。
    有序性：一个线程观察其他线程中的指令执行顺序，由于指令重排序的存在，该观察结果一般杂乱无序。

#### LongAdder  和  AtomicLong 区别？
   [https://blog.csdn.net/yao123long/article/details/63683991]
    
# 锁  互斥  

    synchronized : 依赖java虚拟机
        不可中断的锁，适合竞争不激烈，可读性好。
    Lock :jdk 通过特殊的Cpu指令，代码实现，ReentranLock
        可以中断的锁，多样化同步，竞争激烈时能维持常态。
    Atomic:
    竞争激励的时能维持常态，比Lock性能好，只能同步一个值    

####synchronized  同步锁

    修改代码块 ：大括号括起来的代码，作用于调用的对象
    修饰方法   ：整个方法，作用于调用的对象
    修饰静态方法 ：整个静态方法，作用于所有对象
    修饰一个类  ： 括号括起来的部分，作用于所有对象


# 可见性
    导致共享变量在线程间不可见的原因？
    
    线程交叉执行
    重排序结合线程交叉执行
    共享变量更新后的值没有在工作内存与主存间及时更新

###JMM 关于synchronized的两条规定：

    线程解锁前，必须把共享变量的最新值刷新到主内存中。
    线程加锁时，将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新读取最新的值（注意：加锁和解锁是
    同一把锁）

#### volatile

    对于可见性，Java提供了volatile关键字来保证可见性。
    当一个共享变量被volatile修饰时，它会保证修改的值会立即被更新到主存，当有其他线程需要读取时，它会去内存中读取新值。
    
    通过加入内存屏障和禁止重排序优化来实现：
       对volatile变量写操作时，会在写操作后加入一条store屏障指令，将本地内存中的共享变量值刷新到主内存中去。
       对volatile变量读操作时，会在读操作前加入一条load屏障指令，从主内存中读取共享变量。
   
  
# 有序性

    java内存模型中，允许编译器和处理器对指令进行重排序，但是重排序过程不会影响到单线程程序的执行，但是却会影响到多线程的
    并发执行的正确性。

    主要包含： volatile 、 synchrionized 、lock 
    先天的有序性
    
   
#### Happens before 原则(深入理解java虚拟机)

    1)程序次序规则：一个线程内，按照代码顺序，书写在前面的操作先发生于书写在后面的操作。
    2)锁定规则 ：一个unlock操作先行发生于后面对同一个锁的lock操作
    3)volatile 变量规则：对一个变量的写操作先发生于后面对这个变量的读操作
    4)传递规则：如果操作A先行发生于操作B，而操作B又先行发生于操作C，则可以得出结论，操作A先行发生于操作B
    5)线程启动原则：Thread对象的start()方法先行发生于次线程的每一个动作 
    6)线程中断规则：对线程Interrupt()方法的调用先行发生于被中断线程的代码检测到中断事件的发生
    7)线程终结规则：线程中所有的操作都先行发生于线程的终止检测，我们可以通过Thread.jion()方法结束、
    Thread.isAive()的返回值手段检测到线程已经终止执行
    8)对象终结规则：一个对象的初始化完成先行发生于他的finalize()方法开始。

    除了这八条规则，虚拟机就可以随意对他们进行重排序。

###线程的安全性

    原子性： Atomic包、CAS算法、synchronized 、lock
    同一时间内只有一个线程可以对其进行访问和修改。
    
    可见性：synchronized、 volatile
    一个线程对主内存的修改可以及时的被其他线程知道。
        
    有序性： happens - before
        