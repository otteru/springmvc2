package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//스트링 반환을 했을 때, 뷰리졸버로 가져가지 않고 그냥 바로 화면에 출력함
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());//getClass 내 클래스 넣을 때 사용, 직접 이름.class 넣어도 상관 x

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log={}", name); // 중괄호랑 변수랑 치환된다.
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
