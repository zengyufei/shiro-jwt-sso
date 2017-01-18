服务器无状态 Shiro JWT 实现，基于 springboot ，无sql脚本负担，开箱即用。

该框架基于 张开涛《跟我学shiro》 第二十章 无服务shiro，大多数代码都没更改，直接移植到springboot 上。

我个人理解就是不需要 session，但不需要 session之后仍然需要验证的功能，根据无session认证的解决方案，使用 JWT；

JWT 需要自己实现或使用其他JWT标准的框架，大多数是基于 http header实现，之前接触过 KISSO，觉得放在 cookies里面一样安全，所以使用了这个框架。


《跟我学shiro》第二十章：http://jinnianshilongnian.iteye.com/blog/2041909

kisso: https://www.oschina.net/p/kisso

另外一个基于 Spring-security-jwt实现（本文参考例子）：https://github.com/itweet/jwt-spring-security-demo