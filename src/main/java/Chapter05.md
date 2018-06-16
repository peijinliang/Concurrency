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
   

    















