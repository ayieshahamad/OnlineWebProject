/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2018-09-06 01:29:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class verification_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/header.html", Long.valueOf(1536130224005L));
    _jspx_dependants.put("/loggedOutMenuBar.html", Long.valueOf(1536193710643L));
    _jspx_dependants.put("/footer.html", Long.valueOf(1535179893260L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/icon?family=Material+Icons\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n");
      out.write("    <title>Verification</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    ");
      out.write("<div style=\"background-color: #008B8B; color:FFFFFF; height:130px; margin-left : 35px; margin-right : 35px\">\r\n");
      out.write("\r\n");
      out.write("<h1 style=\"margin-left: 40px ; \">Online Market Place</h1>\r\n");
      out.write("\r\n");
      out.write("</div>");
      out.write("\r\n");
      out.write("    <div style= \"height:400px; margin-left : 35px; margin-right : 35px; margin-top:10px;\">\r\n");
      out.write("        ");
      out.write("<button id=\"b1\" type=\"submit\" form=\"form1\" value=\"Submit\" class=\"w3-button w3-round w3-border w3-border-teal\" style=\"color:#008B8B\"><b>Home</b></button>\r\n");
      out.write("<button id=\"b2\" type=\"submit\" form=\"form2\" value=\"Submit\" class=\"w3-button w3-round w3-border w3-border-teal\" style=\"color:#008B8B\"><b>Login</b></button>\r\n");
      out.write("<button id=\"b3\" type=\"submit\" form=\"form3\" value=\"Submit\" class=\"w3-button w3-round w3-border w3-border-teal\" style=\"color:#008B8B\"><b>Register</b></button>\r\n");
      out.write("<button id=\"b4\" type=\"submit\" form=\"form4\" value=\"Submit\" class=\"w3-button w3-round w3-border w3-border-teal\" style=\"color:#008B8B\"><b>About</b></button>\r\n");
      out.write("\r\n");
      out.write("<button type=\"submit\" form=\"searchForm\" value=\"Search\"  class=\" material-icons w3-button w3-round w3-border w3-border-teal\" style=\"float:right; color:#008B8B\"><b>search</b></button>\r\n");
      out.write("<input value=\"\" type=\"text\" name=\"search\"  style=\"float:right; width:300px; height:40px; margin-right:5;\" class=\"w3-round w3-border w3-border-teal\"/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<form action=\"home\" method=\"get\" id=\"form1\"> </form>\r\n");
      out.write("<form action=\"login\" method=\"get\" id=\"form2\"> </form>\r\n");
      out.write("<form action=\"register\" method=\"get\" id=\"form3\"> </form>\r\n");
      out.write("<form action=\"about\" method=\"get\" id=\"form4\"> </form>\r\n");
      out.write("<form action=\"SearchAd\" method=\"post\" id=\"searchForm\"></form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <!--Remaining section-->\r\n");
      out.write("        <div style=\"border-style:dotted; border-color: green; border-width:1; background-color:MintCream; width:800px; height:200; margin:30;\">\r\n");
      out.write("            <p style=\"margin:30;\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${message}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(" </p>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("    ");
      out.write("<div  style=\"background-color: #008B8B; color:FFFFFF; height:35px; margin-left : 35px; margin-right : 35px\">\r\n");
      out.write("\r\n");
      out.write("    <p></p>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
