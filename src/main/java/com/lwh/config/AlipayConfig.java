package com.lwh.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
	
	// https://www.cnblogs.com/niceyoo/p/12196095.html  操作步骤可参考
	
	// uwxabc5362@sandbox.com  111111
	
	
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
    public static String APP_ID = "2021000116696017";   //你的沙箱APPID

    // 商户私钥，您的PKCS8格式RSA2私钥
    //public static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCJAxfRgbTTSINDOTsdKG7dyleKsg9e83RCkRPD0emPip22nznC3/1eVBSOHRTtxFnonSk/hDa7kY6A5XMdwpH33Ici8TIx+bgLLb0aNuJqVcMTEqAvUI+5TNEiJ38oBQhYSQU653uxe+ehM0MwLhTLPwWzHDtiMQaamPN4VYu17pY+JLVvVo9a6ii1WhcPHH6avKKTKGiv91eiHIO8rNqhpr8bA0r+TrBHuOoWDDp73pwd+dWegWak7ntO3RMTuz0gYQFtnfnpsu0DjQO55NbKwDrSw91ZnOnF4YUVRRSQ376ob1xdJaipkP6WgRwRAxGq3HIJ0KS/739G9/LaecQhAgMBAAECggEAEZHH+CG94momCvTH09FfhilwWGTE+9QUUITyF+ZPIQ0RdF2utoKqpvH5QNAR4vuO2/lrVK3LpvnfYEAMJwDQXwx0d9KeMTJom2ZeHYJ0PlaJEWUODYMzKvvixZJzB9q9WzTh6s/MWNIevdkpAICoQffu7yzroIfQ5PpAHmo55DZnGgJqfbtzty6d+ifR1RU602v5b8guoVQ630DuWjbHHoSM0wjHDiYFxMNPWFKKT8R5RDOEplirCQj4lk144qd+qfhpvV8mxFaV0U7cJ4dyBXFKgq4O/MuwSJ7R1O/xp03IflOZGvTqS8pOMhx8AhGZ6KcOaLgQe/eNzUs0X8O2kQKBgQDl6PEMEvRYYdVeVG2mFCWVMJuSieRaePTwXXlZNgxb8YmpQc/kawR+jyol3WxV1aBkIOLZr7RoZ2A+rbqmRPAo5Y6GDNplPUUZjdjM9wnUOLkzYOyJ36k4kI00AYx7kuBplrsmHc8pHD6N/+7MG3UR3rZ7AAIHy41hpdvgQ9vRcwKBgQCYj2X4EcEW2Ne1w1IvCKPQqOqxXmMswJvslYyyiGuSoZ+5n4S7DEA0j3Zk045jixu0X6PQIg7ewIppAPcSg/fBseUC8GTm0FUltachuxHLOZZxsiirPa2Y8483QMASvW+PL8wcdb3ocMqkXD9FKK5pP4H85M/qsR9pZGY5+etfGwKBgCvNU49fVRo5mXMdVr6bfAWOezVR/CWYgeIBjQsIFxa1T+rlVPxJv1N1ZyxLoUZBKwuokE+1AAQcHeQTKUpgO/kusxarADjlkXKq6bEzJ7Y9EFvPjYE/EXhrYduUp/UrKII1a3Z5m2xTqz9WkWJloMpEyjNo7voZN9hwxxFVHF4tAoGAZ2QvqApoQNKehjmkNen2E1CXFbOxJMA4LANCQjCALvTIKeW474ci8DwV8ckKY93/3aM/gqvCRiZY50bXKz4yp5unZ/GBgIfKXO8aIJsGOSODGPSz6TG4t8LA2aJRSaIGvBSZkOQe2yutuewmmPk0EnIO51QusJBviF6ZhUshv+cCgYAMrMVa2U80GabckkKplsJf4V3iphQnYXALPJA2XZGK0pBdDxWy1Fl7pR/PEQDijyMNT/J+mXzn0y3U1UZdaocpL2mHwe5dcOzSRYNgTdvkY+5mklsj9m9c13l+0qWFEqs+OpURI3soUGg6BWzAqUB1XrIksLcvKoObQvFoFFrz6Q==";
    public static String APP_PRIVATE_KEY = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCEcaEJKCopCFkD8wMOd22bPn4cb6OMQyM5MlKzX0Wpp1K2engG9RP0rwvFSaXalD+wnGYmoIdM/AvMFiDwKegUnt8PjuLLKwU/CtdY843oo1m2CRl+88MrLjQwiroz7gEdgQsXykGSmCyj6+DKWizV0KJjRVh1WjRSvWxilH8ZqfnezLDMWWSSlctSlj0NlUm67p7GQPbzEK3UgsXA12leS2whHo5ru8HoEsim4O+BrcsAEWNtEwijDa4eIpeJjKPtocgbg8CL9jJ0anv/c0zINZ2nz5kDzRc7FsyU6qCdK0JdYmtzGrsvBPt/9YInHeULxqpsjd1l3oXSj1hFDxNpAgMBAAECggEAR8fJzkutZeW3UNOhIXMKvWvGL6U1z2wHqBUlC0AkYklkI7AGY0bqVN+WEovSzr8wMYjiOCcijU/BGRAZM/CSbrKYFDeNpS0gv8UhZ31AuQfnNCiEX/C/IyT8dh6JwmFIy3PHzlH47fl5oDHT9/edexiDGihYQ+nql7lAB8GRjRdx0agyP3EWCEr9OB8GOkKWVqq/N3x1XCBkB9WLaC82cRhegI2GdUKmXqI0GVURb7pT0ZE2XoELhyZ2OQI14mVneNfIl3ZOFANeuyHFg7aYLKRsG1mt229Set+K2aUK5yBna9l9j4AVs6p9C2hK9XqJKicLXnUi4112pG6iykKXLQKBgQC58vnu1ZVJzCiLOFlwJoT77H11CS/0ZpOPIRX8hTH1Gbrt9QL3Y3BX2lBc56KshCK0nLgkLbTVWdu5POhC+WfBW+Lk8JuPMQT5/ljDXxNXKEGeb8q8krocPeJJsHD1kU6xcuzZGpiY3sfO94H/U+UL+VFbs6ndKjIPhjNa2d10dwKBgQC2VpROX8S042+53vKVS52X+7YPPDb3HRXqd5eTIQDVGN/dH485v5mRH4HWU4yDopg+huNc9WGD+LIWU3R5KG1oLbKf2xfHiKQnT6hFqi7ve35lO/D9IGDxj3UtnPFHRAbXPnKmUyJYmfgqQ3AVumkcf+po5VtRLQhr4Iqrs60PHwKBgQCOdisp4hTIP1PopblQBRLpd5v+qJVG57exdqXDm8BUiev/FzG4m0Sc6jObmhO1nF6ChXdGsIXU101Rs+o2NaJaL627vONRZzrq1QToWdUVd1AetINcygUwUfEmh7ljSnpB1UzEYG384OGwHUxJc9sNu2urmBBR01wbLLP3rObPrQKBgQCXrOWpXFjQ7/BceWzpbAEoECad/cTrQrKcW+cmaGm82fg+hKYrr4obAk6K7u0QAw0J1kMDbMs1cIcTs1BMCKkZ5AmWmDXrsWp7mPOtH6fzZpL5uyb4KRr7Vg5vZwH9+kSGgFf2q2kCsvGQlpCCQpJ1NqLTpxaTLBa5RguUUN5AmQKBgQCfn8+X0Xrnx0RctP/BMCYfja8Ssudg2cL5t4kg3FmEjepsc+04nFvKhKsjbSB8MG4SpmjbiCbW3JdSvodsRz6WgWiHf6sS+JoFvYsRSUDUx652MbX2N19febUP+tD5+qzkXG4atWaWc/yem7RJWJxn7decMZwbMLn7It9u5NQqCA==";
    
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    //public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmcSS3XsxOmFXQt9KvlQ7eog9gLzDbNXeiEUoxpIMBb1KyQ71p0eycXws9TFcxisvcspLSrvHRwhCjvRk4wdDt6sM/ndW9jvKSUfw7BiPNPnHwq7jUa2F3uUL/uSdEclzAB+Lg2bq87vYIXLc8IOXQNH6zBi6mdON2mH2Lh9XK/xhmazHVaH96MdhEX4H+XwLmrjP079Q0J03N0wLKqFMmuPrYSniZs3pllLr/8BaDrasvYMmvgr1EcpCn/TSFimwXDv64FTUhDLkLeUw9Z7GLU0JXK2mZknkrqDfL9xyEWMnNN16a+aOgTgVtZ8bSqb+8dKOpH05R8fJ90evA+TRxQIDAQAB";
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh0B9TcOkBfh0PLyxpJtb71TyuJcYZ2p3YJ+R0ZyNEi6+5z86SlN4DTVxW5x8vzjb7VT4F0FanCoySakaRXOAklYnH1SROuqOz2kBEUWQzwMAvNJvJERRVQL1AV8gG29An/a8nfs9P634Y//lDvE/8bWibd9u85p5JNi8lAhsgD6XbJdUXdZNktakw7vo/TPMa3mVwaNB5c6P/unuybidbX3LhbJULEnU+YrxjQE1NncQ9//heqUaVN/nFkt2Z5UfUC7abD9jclw0IRtkplTR8P3dRbwKkOCRBP3OkWHArP2kjgdHIS5lBsC258T4aM4gg2ysCRaCvGtZe/+jNA6IRQIDAQAB";
    		
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";
    public static String notify_url = "http://localhost:7708/myPay/toNotify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
//    public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";
    public static String return_url = "http://localhost:7708/myPay/succ";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
