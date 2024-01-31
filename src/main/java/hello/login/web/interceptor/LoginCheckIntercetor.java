package hello.login.web.interceptor;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckIntercetor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉터 실행 {}", requestURI);

        HttpSession session = request.getSession();

        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) ==null ) {
            log.info("미인증 사용자 요청");


            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }
        // 필터와 달리 접근 가능한 url 목록 설정과 검증 기능을 구현하지 않았다.
        // 이유는 WebConfig에서 등록할 떄 설정하기 때문

        return true;
    }
}
