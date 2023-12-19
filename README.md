Writes settings of application into log before Spring will create beans.

Key files:
* [RunListener](src/main/kotlin/com/vygovskiy/settings/RunListener.kt) - custom 
  [SpringApplicationRunListener](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/SpringApplicationRunListener.html) 
  which process environmentPrepared and write settings to log.
* [META-INF/spring.factories](src/main/resources/META-INF/spring.factories) adding RunListener into Spring boot initialization flow

**Take attention**. 

You can't use logger in SpringApplicationRunListener before environment is not prepared.

