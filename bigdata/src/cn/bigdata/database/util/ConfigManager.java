package cn.bigdata.database.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//��ȡ�����ļ��Ĺ����ࣨ����ģʽ��
public class ConfigManager {
	//��ȡ�����ļ�properties.load(inputstream)
	private static ConfigManager configManager;
	private static Properties properties;
	private ConfigManager(){
		String configFile="database.properties";
		 properties=new Properties();
		InputStream inStream=ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		try {
			properties.load(inStream);
			inStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static ConfigManager getInstance(){
		if(configManager==null ){
			configManager=new ConfigManager();
			
		}
		return configManager;
	}
	public String getString(String key){
		return properties.getProperty(key);
	}
}
