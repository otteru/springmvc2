package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // @RestController랑 같은 역할, 메소드에만 적용하고 싶을 때 사용
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge)  {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,// 변수 이름을 파라미터 이름과 맞춰주면 이렇게 더 간단하게 작성 가능
            @RequestParam int age)  {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4( String username, int age)  {
        /**
         * 근데?! 변수 이름 == 파라미터 이름(아까와 같은 조건)이면 걍 @RequestParam 작성 안해도 됨
         * but String, int, Integer 등 단순 타입일 때만!
         * 근데 좀 과한 면이 있긴 함 그래서 v3가 가장 적절하지 않을까????
         */

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age)  {
        /**
         * true이면 무조건 있어야 한다 없으면 튕김
         * default 값은 required = true 임
         * required = false로 했으면 null이 들어갈 수 있기에 int(x) Integer(o)
         * ?username= => 이런 식으로 입력하면 빈 문자로 입력이 "들어왔다고" 인식
         */

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age)  {
        /**
         * 사실 defaultValue가 들어가면 required가 의미가 없어짐
         * 빈 문자인 경우에도 defaultValue 적용
         */

        log.info("username={}, age={}", username, age);
        return "ok";
    }
}
