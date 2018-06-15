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



