
##Java 并发和高并发

###ava 多线程模块：

并发的基本概念：

    同时拥有一个或者多个线程，如果程序在单核处理器上运行，多个线程将交替的换入或者换出内存。
    一个线程对应者Cpu的一个内核。现在系统都是多核处理器，同时支持多个并发一起执行程序。

##CPU 多级缓存

    因为Cpu运算速度飞速提高，而内存的读取速度有限。导致，Cpu一直在等待内存读取存储区的数据，为了加快计算效率，
    使用内存中出现缓存进行，提高读取速度。
    cpu       缓存     主存
    cpu -> cache -> memory

Cpu多级缓存的意义？

    1）时间局部性：如果某一个数据被访问，那么不久的将来，它很可能被再次访问。
    2）空间局部性：如果某个数据被访问，他们与他相邻的数据很快也有可能被访问

Cpu对Cache的状态？

    用于保证多个Cpu cache 之间缓存共享数据的一致性
    M ：Modifier  修改
    E : Exclusive 独享，专用的 
    S : Share 共享 
    I ：Invalid 无效的  
   
    local read    读取本地缓存
    local write   写入本地缓存
    remote read   读取主存中的数据
    remote write  把数据写入主存
  
Java 内存模型（java Memory model ,jmm) 

    Heap  堆 : 运行时动态分配内存  存取速度相对慢
    Stack 栈 ：存取速度块，仅此于计算机里的寄存器， 共享。  1.存储基本对象类型 2.存储空间小 3、生成期数据，不是太灵活
       
    当多个线程，访问同一个变量，他们拥有的是该变量的私有拷贝。
   
    CPU  ->  cpu 寄存器 -> cpu缓存（读写速度非常块的存储区域） ->  内存 
    

Java内存模型  同步八种操作

    Lock 锁定 :用于主内存的变量，把一个变量标识为一条线程独占状态。
    Unlock 解锁 ：用于主内存的变量，把一个处于锁定状态的变量释放出来，释放后的变量可以被其他线程锁定。
    Read 读取：  作用于主内存的变量，把一个变量值从主内存传输到线程的工作内存中去，以便以后load动作使用。
    Load 载入： 工作于内存的变量，把他read操作从主内存的变量值放入工作内存的变量副本中去。
    Use 使用：  作用于工作内存的变量，把工作内存中的一个变量值传给执行引擎。
    Assign 赋值： 作用于工作内存的变量，他把一个从执行引擎接收到的值赋值给工作内存的变量。
    Store 存储： 作用于工作内存的变量，把工作内存中一个变量的值传送哒到主存中，以便随后的write的操作。
    write 写入： 作用于主存的变量，他把store操作从工作内存中一个变量的值传送到主内存的变量中去。
    
 
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
        
# 不可变对象

###不可变对象满足的条件（参考String）：

    对象创建以后其状态就不能修改
    对象所有的域都是final类型的
    对象是正确创建的(在对象创建期间，this引用没有逸出)
    
    将类定义成final 不可以集成
    将成员都变成私有的，不可以直接访问这些成员
    对变量不提供set和get方法
    将所有可以变的成员生命为final，这样通过构造器初始化所有成员，进行深度拷贝

###final 类 方法 变量  关键字：
      
    修饰类： 不能被继承
    修饰方法：
       1、锁定方法不被继承类所修改
       2、效率
    修饰变量： 
       1、基本数据类型初始化之后不可以修改     
       2、引用类型的变量初始化之后不能指向另外一个对象
       
       
# 安全发布对象 (这一节的第一章没有搞清楚)

### 发布对象：使一个对象能够被当前范围之外的代码所使用。
    
### 对象逸出：一种错误的发布。当一个对象还没有构造完成时，就使它被其他线程所见。

##安全发布对象的四种方法？
    
    1)在静态的初始化函数中初始化一个对象引用
    2)将对象的引用保存到volatile类型域或者AtomicReference 对象中
    3)将对象的引用保存到某一个正确的构造对象的final类型域中
    4)将对象引用保存到一个由锁保护的域中

#线程封闭

    把一个对象封闭到一个线程里边，只允许这个对象在这个线程里进行访问。
    
    Ad-hoc 线程封闭：程序控制实现，最糟糕，可以忽略
    堆栈封闭：局部变量，并无并发问题
    TreadLocal 线程封闭:特别好的方法（本质是维护了一个Map,key是线程名称，value对应线程对象）

# 线程不安全的写法
    
    线程不安全       线程安全
    StringBuilder   StringBuffer
    SimpleDateFormat  jodaDateTimeFormatter
    ArrayList、HashMap、HashSet 等Collections 容器均是线程不安全的

#同步容器

    ArrayList -> Vector (矢量)、Stack(栈)
    HashMap   -> HashTable(key,value 不能为空)
    Collections.synchronizedXXX(List、Set、Map) //集合 排序 处理
    
    
    同步容器场景越来越少
    1）synchronized 会降低性能
    2）并不能完全保证线程安全

#并发容器 (J U C)
###ArrayList -> CopyOnWriteArrayList
    原理： copy一个副本，进行写操作，写操作加锁。
    缺点
    1、需要copy数组，就要消耗内存，如果元素比价多的时候，可能导致 gc
    2、不能应用于实时读的场景
    
    适合：读多 写少的操作 
    
    设计思想：
    1、读写分离 
    2、最终一致性
    3、使用时另外开辟控件
    4、读不加锁 写是加锁的(防止多个线程copy出多个副本)

          
###HashSet、TreeSet-> CopyOnWriteArraySet (CopyOnWriteArrayList)、ConcurrentSkipListSet（jdk1.6）

    CopyOnWriteArraySet:
    线程安全
    1、copy开销较大
    2、不支持remove操作
    3、遍历速度非常快，适合读操作大于写操作的情况
    
    ConcurrentSkipListSet:
    支持自然排序、定义比较细
    1、Map
    2、add 、put 、move 线程安全的
    3、不允许使用空元素Null
    4、addAll RoveAll  等是批量线程不安全的

####HashMap、 TreeMap -> ConcurrentHashMap 、ConcurrentSkipListMap
    
    ConcurrentHashMap:
    1、不允许空值
    2、具有特别高的并发性
    
    ConcurrentSkipListMap:
    
    1、SkipList 跳表 
    2、key 是有序  支持更高的并发


####安全共享对象策略

    1）线程限制：一个被线程限制的对象，由线程独占，并且只能被占有他的线程修改
    
    2）共享只读：一个共享只读的对象，在没有额外同步的情况下，可以被多个线程并发访问，但是任何
    线程都不能修改它
    
    3）线程安全对象：一个线程安全的对象或容器，在内部通过同步机制来保证线程安全，所以其他的线程无需额外的同步
    就可以通过公共接口随意访问它
    
    4）被守护对象：被守护对象只能通过获取特定的锁来访问



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
            
#  FutrueTask  (juc 并不是aqs的子类)


实现线程 继承Thread 和 实现Runnable接口，但是都没有办法获得返回值。

jdk1.5 之后 提供了Callable 与 Runnable 接口 来获取线程执行的结果。

Callable 和 Runnable 接口对比？
Callable 功能更加强大一些，被线程执行后，有返回值可以抛出异常。

Future
    接口可以对于线程的中任务可以执行 取消，是否被取消，查询是否完成和获取结果。
    可以监视线程调用Call的情况，调用future.get()方法来获得结果。
    总结一句话： 可以得到别的线程方法的返回值。
     

FutureTask  implements  RunnableFuture 
FutureRunnable 实现了Callable 和 Runnable 接口。

# Fork/Join 框架  

java7中把大任务分割成若干个小任务的，最终汇总每一个小任务结果后，活得大任务结果的框架

Fork：把大任务分割小任务
Join：把小任务进行汇总，得到大人物结果

工作窃取算法： 双端队列，先干完活的线程帮助拿一些没有干完活的的线程干活。
充分利用线程进行并行计算同时减小了线程之间的竞争。

比较适合的任务场景：

#BlockingQueue 阻塞队列

extends Queue<E> extends Collection<>

BlockingQueue<E>中方法：

    boolean add(E e);
    boolean offer(E e);
    void put(E e) throws InterruptedException;
    boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException;
    E take() throws InterruptedException;
    E poll(long timeout, TimeUnit unit) throws InterruptedException;
    int remainingCapacity();    
    boolean remove(Object o);
    public boolean contains(Object o);
    int drainTo(Collection<? super E> c);
    int drainTo(Collection<? super E> c, int maxElements);
          
Queue<E> 中方法：

    boolean add(E e);
    boolean offer(E e);
    E remove();
    E poll();
    E element();
    E peek();

###子类：

ArrayBlockingQueue

    有界(容量有限，在初始化的时候指定容量大小，不可修改)的有序队列，内部实现是一个有序数组。
    先进先出的方式：先进去的在尾部，先出去的是头部。
   
DelayQueue
    
    阻塞的是内部元素
    插入对象必须要实现Delayed接口, Delayed 继承Comparable 排序接口。
    应用场景：定时关闭连接、缓存对象、超时处理等多种场景
    内部实现是：PriorityQueue 和 Lock  (排序跟锁)
    
LinkedBlockingQueue(FIFO)

    大小配置可以选，初始化不指定就是没有边界的。没有边界就以为着用了Integer的最大整型值
    实现：链表
    先进先出的方式：先进去的在尾部，先出去的是头部。
    
PriorityBlockingQueue

    没有边界的排序队列，但是他是有规则的。
    允许插入NULL
    插入对象必须实现Comparable接口，排序规则就是根据Comparable实现来进行定义的
    获得到的迭代器Iterator 但是并不是有序的。
   
SychronousQueue
    
    内部仅仅只允许一个元素，插入一个元素后就被阻塞,除非这个元素被另一个元素所消费。
    同步队列，无界非缓存。
   

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



 

       