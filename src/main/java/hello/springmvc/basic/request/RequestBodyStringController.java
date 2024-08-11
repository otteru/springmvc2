package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }

    /**
     * inputStream(Reader), OutputStream(Writer)을 파라미터로 받을 수 있다
     * 아마 servlet 종속성 문제랑 간단해지니까 더 나은듯
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    /**
     * 메시지 바디 정보 직접 반환
     * 헤더 정보 포함 가능
     * view 조회X
     *
     * String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
     * 이 코드를 알아서 HttpEntity가 <>안에 있는 타입으로 만들어 놓는다.
     *
     * 반환 타입은 response 어떻게 보내느냐에 따라 <>안에 타입 넣으면 됨
     *
     * 쿼리 파라미터나 HTML Form을 사용하는 경우에만 @RequestParam, @ModelAttribute를 사용하고 그 외에는 HttpEntity 같이 데이터를 직접 꺼낸다.
     */
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        //

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    /**
     * 요청은 @RequestBody가 응답은 @ResponseBody가 처리
     */
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}", messageBody);

        return "ok";
    }

    /**
     * 다시 한번 말하지만 얘네는 @RequestParam, @ModelAttribute랑은 전혀 관계가 없고
     * HttpMessageConverter라는 다른 매커니즘이 적용된다.
     *
     * 요청 파라미터 -> @RequestParam, @ModelAttribute
     * HTTP 메세지 바디 -> @RequestBody
     */
}
