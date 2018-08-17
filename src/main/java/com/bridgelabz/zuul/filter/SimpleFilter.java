package com.bridgelabz.zuul.filter;

import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import com.bridgelabz.zuul.util.JWTException;
import com.bridgelabz.zuul.util.JWToken;
import com.netflix.zuul.ZuulFilter;

/****************************************************************************************
 * @author Ankita Kalgutkar
 *
 *Purpose:Zuul Filter 
 *****************************************************************************************/
public class SimpleFilter extends ZuulFilter 
{
 
  @Override
  public boolean shouldFilter() 
  {
    return true;
  }
  
  @Override
  public Object run()
  {
	  RequestContext ctx = RequestContext.getCurrentContext();
	  HttpServletRequest request = ctx.getRequest();
	  
	  if(request.getRequestURI().matches("(.*)/note(.*)"))
	  { 
		  String userId = JWToken.verifyToken(request.getHeader("Authorization")).getIssuer();
		  if(userId==null)
		  {
			  throw new JWTException("User doesn't exists");
		  }
		  System.out.println(userId);
		  ctx.addZuulRequestHeader("userId",userId);
	  }
	  return true;
  }
	
  @Override
  public String filterType() 
  {
    return "pre";
  }

  @Override
  public int filterOrder() 
  {
    return 1;
  }
}
