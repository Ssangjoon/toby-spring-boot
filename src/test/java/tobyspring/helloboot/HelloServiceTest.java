package tobyspring.helloboot;

import com.fasterxml.jackson.databind.node.BaseJsonNode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


public class HelloServiceTest {
    @Test
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();
        String ret = helloService.sayHello("Test");

        assertThat(ret).isEqualTo("Hello Test");
    }
    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator( name -> name);

        String ret = decorator.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
