package jp.sundevapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jp.sundevapp.base.LoginUserInfo;

public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private LoginUserInfo loginUserInfo;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println(request.getRequestURL());
		if (StringUtils.isEmpty(loginUserInfo.getUserId())) {
			response.sendRedirect("/login");
			return false;
		}
		request.setAttribute("loginUserInfo", loginUserInfo);
		return true;
	}
}
