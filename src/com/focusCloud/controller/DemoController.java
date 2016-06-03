package com.focusCloud.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.focusCloud.controller.common.Log;
import com.focusCloud.controller.common.MessageManager;
import com.focusCloud.controller.common.MyX509TrustManager;
import com.focusCloud.model.UserInfo;


@Controller
@Scope("prototype")
public class DemoController {
	@InitBinder("userInfo")
	public void initBinder(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userInfo.");
	}
	
	@RequestMapping("/demo")
	public String demo()
			throws Exception {
		
		return "auguryTest";
	}
	
	@RequestMapping("/index")
	public String Index(ModelMap model, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Log log = Log.getLogger();
		log.logger.info("indexindexindex");
		log.logger.info("+++++++signature: ");
		
		// 微信加密签名  
        String signature = request.getParameter("signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");  
        
        log.logger.info("+++++++signature: "+signature);
        log.logger.info("+++++++timestamp: "+timestamp);
        log.logger.info("+++++++nonce: "+nonce);
        log.logger.info("+++++++echostr: "+echostr);
		
		return "auguryTest";
	}
	
	@RequestMapping("/token")
	public String token(ModelMap model, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Log log = Log.getLogger();
		log.logger.info("+++++++signature: ");
		
		// 微信加密签名  
        String signature = request.getParameter("signature");  
        // 时间戳  
        String timestamp = request.getParameter("timestamp");  
        // 随机数  
        String nonce = request.getParameter("nonce");  
        // 随机字符串  
        String echostr = request.getParameter("echostr");  
        
        log.logger.info("+++++++signature: "+signature);
        log.logger.info("+++++++timestamp: "+timestamp);
        log.logger.info("+++++++nonce: "+nonce);
        log.logger.info("+++++++echostr: "+echostr);
        
  
        
        
        PrintWriter out = response.getWriter();  
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
        
        out.close();  
		return "focusVan";
	}
	
	// 测试
	@RequestMapping("/test")
	public String test(ModelMap model, String id, UserInfo userInfo,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		return "auguryResult";
	}
	
	
	// 获取用户地理位置
	/**
	 * @param model
	 * @param id
	 * @param userInfo
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/send")
	public String sendWeatherMessage(ModelMap model, String id, UserInfo userInfo,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		Log log = Log.getLogger();
		String accessToken = getAccessToken();
		
		log.logger.info("++++++++++ token: "+accessToken);
		String userListUrl = "http://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken;
		String users = getHtmlConentByUrl(userListUrl);
		log.logger.info("++++++++++ users: "+users);
		
		JSONObject userJo = JSONObject.fromObject(users);
		JSONObject userData = userJo.getJSONObject("data");
		JSONArray openids =  userData.getJSONArray("openid");
		
		for(int i=0,len=openids.size();i<len;i++){
			String tempOpenId = openids.getString(i);
			String infoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+tempOpenId+"&lang=zh_CN";
			String infoStr = getHtmlConentByUrl(infoUrl);
			log.logger.info("++++++++++++ userInfo: "+infoStr);
			JSONObject infoJo = JSONObject.fromObject(infoStr);
			String palace = "";
			String city = infoJo.getString("city");
			if(!"".equals(city)){
				palace = city;
			}else{
				palace = infoJo.getString("province");
			}
			
			if(palace != null && !"".equals(palace)){
					String cityName = URLEncoder.encode(palace,"utf-8");
					String url = "http://op.juhe.cn/onebox/weather/query?key=a84636c0847994e82e3ec8f170b1fe9d&cityname="+cityName;
					String content = getHtmlConentByUrl(url);
					log.logger.info("+++++++++ weather: "+content);
					JSONObject jo = JSONObject.fromObject(content);
					if("暂不支持该城市".equals(jo.getString("reason"))){
						palace = infoJo.getString("province");
						cityName = URLEncoder.encode(palace,"utf-8");
						url = "http://op.juhe.cn/onebox/weather/query?key=a84636c0847994e82e3ec8f170b1fe9d&cityname="+cityName;
						content = getHtmlConentByUrl(url);
						log.logger.info("+++++++++ weather222222: "+content);
						jo = JSONObject.fromObject(content);
					}
					JSONObject result = jo.getJSONObject("result");
					JSONObject data = result.getJSONObject("data");
					JSONArray weather = data.getJSONArray("weather");
					JSONObject today = weather.getJSONObject(0);
					JSONObject info = today.getJSONObject("info");
					
					JSONArray day = info.getJSONArray("day");
					String weatherFinal = day.getString(1);
					String highTemp = day.getString(2);
					JSONArray night = info.getJSONArray("night");
					String lowTemp = night.getString(2);
					
					JSONObject life = data.getJSONObject("life");
					JSONObject lifeInfo = life.getJSONObject("info");
					JSONArray chuanyi = lifeInfo.getJSONArray("chuanyi");
					String chuanyiStr = chuanyi.getString(0)+","+chuanyi.getString(1);
					JSONArray ganmao = lifeInfo.getJSONArray("ganmao");
					String ganmaoStr = ganmao.getString(0)+","+ganmao.getString(1);
					
					JSONObject pm25 = data.getJSONObject("pm25");
					JSONObject pm = pm25.getJSONObject("pm25");
					String pmValue = pm.getString("pm25").trim();
					String pmLevel = pm.getString("level").trim();
					String pmQuality = pm.getString("quality").trim();
					String pmInfo = pm.getString("des").trim();
					
					String tempture = lowTemp+"~"+highTemp+ "\u2103";
					String pm25Value = "PM2.5指数是"+pmValue+"，"+"等级为"+pmLevel+"，质量为 "+pmQuality+"。"+pmInfo;
					
					String hello = palace;
					String templateId = "yqXFVyU4XfrEvcOi-bRneEJs5NWQHQICwVNxgjYor2c";
					String json = " {\"touser\":\"" + tempOpenId + "\",\"template_id\":\"" + templateId + "\",\"url\":\"" + ""
		            + "\",\"topcolor\":\"#FF0000\",\"data\":{\"hello\": {\"value\":\"" + hello + 
		            "\",\"color\":\"#173177\"},\"weather\":{\"value\":\"" + weatherFinal + 
		            "\",\"color\":\"#173177\"},\"temp\":{\"value\":\"" + tempture + 
		            "\",\"color\":\"#173177\"},\"pm\":{\"value\":\"" + pm25Value + 
		            "\",\"color\":\"#173177\"},\"clothes\":{\"value\":\"" + chuanyiStr + 
		            "\",\"color\":\"#173177\"},\"cold\":{\"value\":\"" + ganmaoStr + 
		            "\",\"color\":\"#173177\"}}}";
					log.logger.info("+++++++++++++++json: "+json);
					String str= "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
					log.logger.info("+++++++++++++++openid: "+tempOpenId);

					if("oLbySsx_i2VKwwd0OXdN9b4HdNq8".equals(tempOpenId)){
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR, 8);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND,0);
						Calendar current = Calendar.getInstance();
						if(current.after(cal)){
							JSONObject returnJo = httpRequest(str, "POST", json);
							log.logger.info(returnJo);
							log.logger.info("++++++++++++++++++++ cloud finsh");
							return "finish";
						}
					}else{
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.HOUR, 8);
						cal.set(Calendar.MINUTE, 0);
						cal.set(Calendar.SECOND,0);
						Calendar current = Calendar.getInstance();
						if(current.before(cal)){
							JSONObject returnJo = httpRequest(str, "POST", json);
							log.logger.info(returnJo);
							log.logger.info("111111111111111111finsh");
						}
					}
					
				}
			}
		
		return "success";
	}
	
	
	@RequestMapping("/ancientTest")
	public String ancientTest(ModelMap model, String id, UserInfo userInfo,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		model.addAttribute("currentDate", sdf.format(today));
		return "ancientTest";
	}
	
	// 老黄历
	@RequestMapping("/ancient")
	public String ancient(ModelMap model, String id, UserInfo userInfo,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		String date = userInfo.getDate();
		String url = "http://v.juhe.cn/laohuangli/d?key=34ba7420e1f79634b924b1468edc791b&date="+date;
		String content = getHtmlConentByUrl(url);
		
		
		JSONObject jo = JSONObject.fromObject(content);
		JSONObject result = jo.getJSONObject("result");
		String  yangli = result.getString("yangli");
		String yinli = result.getString("yinli");
		String wuxing = result.getString("wuxing");
		String chongsha = result.getString("chongsha");
		String baiji = result.getString("baiji");
		String jishen = result.getString("jishen");
		String yi = result.getString("yi");
		String xiongshen = result.getString("xiongshen");
		String ji = result.getString("ji");
		if("2017-01-30".equals(date) || "2017-1-30".equals(date)){
			yi=" 嫁娶  招婿  "+yi;
			ji=("破土 安葬");
		}
		
		model.addAttribute("yangli", yangli);
		model.addAttribute("yinli", yinli);
		model.addAttribute("wuxing", wuxing);
		model.addAttribute("chongsha", chongsha);
		model.addAttribute("baiji", baiji);
		model.addAttribute("jishen", jishen);
		model.addAttribute("yi", yi);
		model.addAttribute("xiongshen", xiongshen);
		model.addAttribute("ji", ji);
		
		return "ancientResult";
	}
	
	
	// 获取页面内容
	public static String getHtmlConentByUrl(String ssourl) {
		try {
			URL url = new URL(ssourl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setInstanceFollowRedirects(false);
			con.setUseCaches(false);
			con.setAllowUserInteraction(false);
			con.connect();
			StringBuffer sb = new StringBuffer();
			String line = "";
			BufferedReader URLinput = new BufferedReader(new InputStreamReader(
					con.getInputStream(),"utf-8"));
			while ((line = URLinput.readLine()) != null) {
				sb.append(line);
			}
			con.disconnect();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String getHtmlContentOfPost(String action,String menu){
		try {
	           URL url = new URL(action);
	           HttpURLConnection http =   (HttpURLConnection) url.openConnection();    

	           http.setRequestMethod("POST");        
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
	           http.setDoOutput(true);        
	           http.setDoInput(true);
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
	           http.connect();
	           OutputStream os= http.getOutputStream();    
	           os.write(menu.getBytes("UTF-8"));//传入参数    
	           os.flush();
	           os.close();

	           InputStream is =http.getInputStream();
	           int size =is.available();
	           byte[] jsonBytes =new byte[size];
	           is.read(jsonBytes);
	           String message=new String(jsonBytes,"UTF-8");
	           return "返回信息"+message;
	           } catch (Exception e) {
	               e.printStackTrace();
	           }  
	        return "createMenu 失败";
	}
	
	public String getAccessToken() throws Exception{
		Log log = Log.getLogger();
		String appid = MessageManager.getMsg("appidTest");
		String appsecret = MessageManager.getMsg("appsecretTest");
		log.logger.info("++++++++++++++++++++ appid: "+appid);
		log.logger.info("++++++++++++++++++++ appsecret: "+appsecret);
        String strUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
        String outputStr = "";
        JSONObject jo1 = httpRequest(strUrl, "POST", outputStr);
        String access_token = "";
        try{
        	access_token = jo1.getString("access_token");
    		log.logger.info("++++++++++++++++++++ access_token: "+access_token);
        }catch(Exception e){
        }
        
        return access_token;
	}
	
	
	/**
	 * 
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */

	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		Log log = Log.getLogger();
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();

			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();

			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			log.logger.info(e);
			log.logger.info(e.getMessage());
			log.logger.info(e.getCause());
		}

		return jsonObject;

	}
	
	
	public boolean validate(String accessToken) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token="+accessToken;
		String json = "{}";
		JSONObject jsonObj = httpRequest(url, "POST", json);
		
		if(jsonObj.containsKey("errcode")){
			return false;
		}else{
			return true;
		}
	
	}
	
	
}
