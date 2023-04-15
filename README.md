This is an example of how CQRS can be implemented without an Event Source in Java and without the Saxon library.
As a dispatcher of commands and requests, the application context is used, from which the desired handler is obtained by the name of its bean. To do this, a map is preliminarily formed in BeanPostProcessor, where the command class is added - the name of the handler.
