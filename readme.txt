面向切面编程（AOP）
理解：
    一个程序往往存在着类似的方法，例如数据库操作的方法，在调用前需要打印日志，调用后又要管理事务，而这些调用前行执行的内容其实是类似的，面向切面编程的作用，就是把这些调用前后的行为抽离出来，只写一份，简化代码。
名词：
    Aspect（切面）：抽离出来的行为用代码写到方法里面，方法所在的类就是切面类
    JoinPoint（连接点）：数据库操作可以是一个连接点，异常抛出可以是一个连接点，我们会在连接点的前后调用切面方法
    Pointcut（切入点）：切入点和连接点其实差不多，我认为可以一起理解
    Advice（通知/增强处理）：切面类中的方法，它是切面的具体实现
    Target Object（目标对象）：被增强对象，就是数据库操作所在的类对象
    Proxy（代理）：讲通知应用到目标对象之后，被动态创建的对象
    Weaving（织入）：将切面代码插入到目标对象上，从而生成代理对象的过程
织入过程理解：
    通过源码可以看到，新对象对目标对象的接口有一个克隆的操作，通过反射机制，在目标对象的基础上，重新生成了一个带有增强方法的新对象
    从另外一个角度比喻，就是好像jsp一样重新写了一个新的类，然后编译，然后再把类加载到内存中，当然，这只是一个比喻，应该不会重新编写类

目录说明：
com.guxiu.jdkaop：使用jdk动态代理的AOP编程
    JDK动态代理的使用非常简单，但还有一定的局限性——使用动态代理的对象必须实现一个或多个接口
com.guxiu.cglibaop：使用cglibaop代理的AOP编程
    1）开发前需在pom.xml文件中引入cglib的maven依赖
    2）弥补JDK动态代理的缺点，可以对没有实现接口的类进行代理，简单说，目标类可以直接用class，不用interface
com.guxiu.springaop：基于代理类的AOP实现（比较旧的Spring实现方式）
    1）Spring的通知类型
        环绕通知（org.aopalliance.intercept.MethodInterceptor）
            在目标方法执行前后实施增强，可以应用于日志、事务管理等功能
        前置通知（org.springframework.aop.MethodBeforeAdvice）
            在目标方法执行前实施增强，可以应用于权限管理等功能
        后置通知（org.springframework.aop.AfterReturnfingAdvice）
            在目标方法执行后实施增强，可以应用于关闭、上传文件、删除临时文件等功能
        异常通知（org.springframework.aop.ThrowsAdvice）
            在方法抛出异常后实施增强，可以应用于处理异常记录日志等功能
        引介通知（org.springframework.aop.IntroductionInterceptor）
            在目标类仲添加一些新的方法和属性，可以应用于修改老版本程序（增强类）
    2）ProxyFactoryBean
        ProxyFactoryBean是FactoryBean几口的实现类，FactoryBean负责实例化一个Bean，而ProxyFactoryBean负责为其他Bean创建代理实例。在Spring仲，使用ProsyFactoryBean是创建AOP代理的基本方式
    3）开发前需在pom.xml文件中引入spring-aop和aopalliance的maven依赖，由于使用spring框架，所以也是必须因纳入commons-logging、spring-context、spring-core、spring-beans和spring-expression五个基础的包
com.guxiu.aspectj：使用AspectJ框架的AOP编程（Spring推荐）
    1）开发前需在pom.xml文件中引入spring-aspects和aspectjweaver的maven依赖
com.guxiu.aspectjannotation：使用AspectJ框架注解的AOP编程（更方便）
    编写这个例子的时候遇到比较多的错误
    1）org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'userDao' available
        找不到对应的bean，经过测试，貌似使用接口编程的方式不太行，于是修改成UserDao类，并添加@Component注解，让spring自动生成bean
    2）Initialization of bean failed; nested exception is java.lang.IllegalArgumentException: error at ::0 can't find referenced pointcut myPolintCut
        aspectjweaver和aspectjrt使用版本太低，百度搜索出来的版本最高只有1.5.4，但是原来有更高版本，使用1.7.4即可解决
    3）Initialization of bean failed; nested exception is java.lang.IllegalArgumentException: error at ::0 formal unbound in pointcut
        这个和第2个错误很相似，原因是后置通知@AfterReturning注解中必须填写returning选项，否则就会报异常

关于<aop:aspect>和<aop:advisor>的异同
<aop:aspect>
1、大多用于日志，缓存
2、定义切面（切面包括通知和切点）
3、定义切面时，只需要定义一般的bean就行
<aop:advisor>
1、大多用于事务管理
2、定义通知器（通知器跟切面一样，也包括通知和切点）
3、引用的通知时，通知必须实现Advice接口