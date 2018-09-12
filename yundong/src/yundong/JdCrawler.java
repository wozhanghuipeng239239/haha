package yundong;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;



public class JdCrawler {
	
	public static void main(String[] args) throws IOException {
		JdCrawler ss=new JdCrawler();
	ss.happy();
		
	}
	
	public static void happy() throws IOException{
		Connection conn=null;
		Statement stat=null;
		int count=0;
		int s=1;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/zongku","root","root");
			stat=conn.createStatement();
				conn.setAutoCommit(false);
			//stat.addBatch("use zongku");
		for(int i=1;i<=1;i++){
		String url="http://www.yoger.com.cn/products.php?cid=41&nowpage="+i;
	Elements els  =	Jsoup.connect(url).get()
                    .select("div .a1 a");
	int j=0;
		for(Element o:els){
			
			String itemUrl=o.attr("href");
			
			String xx=o.attr("id");
			//String xx=Jsoup.connect(itemUrl).get().select("div .xq_xinxi h1").text();
			
			
		String kaka=getNum(itemUrl);
		//String gogo="https://p.3.cn/prices/mgets?&skuIds=J_"+kaka;
		//String gogo="http://www.yoger.com.cn/product/"+kaka+".html";
//		String bb=Jsoup.connect(gogo).ignoreContentType(true).execute().body();
//		ObjectMapper  MAPPER= new ObjectMapper();
//		JsonNode node=MAPPER.readTree(bb);
//		String price= node.get(0).get("p").asText();
		
		Element ss  =	Jsoup.connect(url).get()
                .select("div .cpjg p").get(j);	
		System.out.println(j);
		j++;
		
		String price=ss.attr("id").toString();
		int kucun=(int)(Math.random()*1000);
		
		//System.out.println("insert into phone_jingdong(url,Price,detail,kucun)values('"+itemUrl+"','"+price+"','"+xx+"',"+kucun+");");
		//String pp="insert into badminton(itemId,url,price,detail,store)values("+kaka+",'"+itemUrl+"',"+price+",'"+xx+"',"+kucun+")";
		String pp="insert into skiing(itemId,url,price,detail,store)values("+kaka+",'"+itemUrl+"',"+price+",'"+xx+"',"+kucun+")";
		System.out.println(pp);
		stat.addBatch(pp);
//		stat.executeBatch();
		stat.executeBatch();
		conn.commit();
		count++;
		
		
		
		}
		
		s++;
		System.out.println(s);
		}
	System.out.println("执行完成共:"+count+"条记录！");
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}finally{
			try {
				conn.close();
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn=null;
				stat=null;
			}
			
		}
		}

	public  static String getNum(String a){
		char [] qq=a.toCharArray();
		char [] zz=new char[0];
		String ss="";
		for(int i=0,j=0;i<a.length();i++){
			if(qq[i]>=48&&qq[i]<=57){
				zz=Arrays.copyOf(zz, zz.length+1);
				zz[j]=qq[i];
				ss+=zz[j];
				j++;
			}
		}
		return ss;	
}
}