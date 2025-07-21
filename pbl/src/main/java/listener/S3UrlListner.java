package listener;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.apache.ibatis.session.SqlSession;

import mapper.CategoryMapper;
import util.MyBatisUtil;
import util.PropsLoaderUtil;

@WebListener
public class S3UrlListner implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		Properties props = PropsLoaderUtil.getProperties("secret/aws_s3.properties");
		String s3url = String.format("https://%s.s3.%s.amazonaws.com/upload/", props.get("bucket-name"),props.get("region-name"));
		sc.setAttribute("s3url", s3url);
		
//		https://ikkikki.s3.ap-northeast-2.amazonaws.com/upload/2025/07/07/t_4d663ea2-879f-416e-b32a-8d6e7c9cb8da.gif
//		/pbl/display?uuid=t_${a.uuid}&path=${a.path}
	}
}