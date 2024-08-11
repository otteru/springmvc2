package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // Logger 선언 대신에 사용 가능
@RestController//스트링 반환을 했을 때, 뷰리졸버로 가져가지 않고 그냥 바로 화면에 출력함(바디에 바로 넣음)
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass());//getClass 내 클래스 넣을 때 사용, 직접 이름.class 넣어도 상관 x

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

//        log.info("info log=" + name); --> 이렇게 log 작성 no!
        // 출력을 안하는 경우에 +(연산)이 일어나면서 메모리를 사용하게 된다. 근데 밑의 방식으로 하면 연산이 안일어나기에 더 효율적!

        log.trace("trace log={}", name); // 중괄호랑 변수랑 치환된다.
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        /**
         * trace - debug - info - warn - error 순으로 level을 설정해서 어느 level까지 보여줄 지 properties에서 정할 수 있다.
         * 콘솔이 아니라 설정하면 파일이나 다른 곳에 전송등을 할 수 있다.
         * 개발 서버는 debug 출력
         * 운영 서버는 info 출력
         * 성능적으로 sout보다 훨씬 좋다.(공부할 때는 상관없지만 실무에서는 무조건 사용!!)
         */

        return "ok";
    }
}
