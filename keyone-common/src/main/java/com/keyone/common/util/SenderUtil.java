package com.keyone.common.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 做返回信息处理的类
 * <p>Title: Sender</p>
 * <p>Description: </p>
 * <p>Company:www.keyonecn.com</p>
 * @author   fzw
 * @date     2017-3-13上午10:40:59
 * @version  1.0
 */
public class SenderUtil extends Thread
{
	

	Socket socket = null;
	public static SenderUtil s = null;
	public static String content="";

	public  void control(String cellcontent) throws Exception
	{
		content=cellcontent;
		s = new SenderUtil();
		s.start();
	}

	public void run()
	{
		Socket socket = null;
		InputStream dos = null;
		String result = null;
		String[] flag = null;
		try
		{
			socket = new Socket("121.199.2.76", 8886);
			if (socket.isConnected())
			{
				System.out.println("已建立连接!---");
				//Thread.sleep(800);
				OutputStream outputStream = socket.getOutputStream();
				//首次发送对应瓶子的id
				outputStream.write(content.getBytes());

				//返回模式发送回去的信息
				//outputStream.write("100_1_1".getBytes());

				//发送细胞id以及模式
				//outputStream.write("100_2_1".getBytes());

				//传代模式输入细胞编号以及模式以及数量加瓶子数量
				// outputStream.write("321312_2_2_10_3_3".getBytes());

				//调用 outputStream.write("id_mode_numberCells_e".getBytes());
				//outputStream.write("321312_2_3_1_3".getBytes());

				//调用 outputStream.write("id_mode_4_id_Toid_BottleRemask".getBytes());
				// outputStream.write("321312_2_4_110_37_堪萨斯".getBytes());

				//调用 换液
				//outputStream.write("321312_3_1".getBytes());

				//返回培养瓶
				//outputStream.write("3211312_4_1".getBytes());

				//返回培养管是否消化
				//outputStream.write("3211312_5_1".getBytes());

				//返回培养管
				//outputStream.write("3211312_5_2".getBytes());

				//获取socket发送过来的输出流
				dos = socket.getInputStream();
				byte[] buffer = new byte[1024];
				int len = -1;
				while ((len = dos.read(buffer)) != -1)
				{
					//接受字符串
					result = new String(buffer, 0, len, "utf-8");
					System.out.println(result);
					flag = result.split("_");
					//开始做逻辑判断
					if (flag.length == 1)
					{
						String first = flag[0];
						if (first.equals("0"))
						{
							//打开显微镜开始观察然后选择模式页面做出响应
							System.out.println("叫我选择模式");
						
							//dos.close();
							// outputStream.write("100".getBytes());
							//确定之后发送培养瓶id与模式
							//调用  outputStream.write("id_mode".getBytes());
						}
					} else if (flag.length == 2)
					{
						String mode = flag[0];
						String nextmode = flag[1];
						if (nextmode.equals("1") && mode.equals("1"))
						{
							//PLC工作结束培养瓶返回模式结束,页面响应
							System.out.println("返回模式结束");
							break;
						} else if (nextmode.equals("1") && mode.equals("2"))
						{
							//PLC工作结束传代模式到达开始计数阶段
							System.out.println("叫我输入细胞技计数以及细胞瓶子的数目");
							break;
							//确定之后发送
							//调用 outputStream.write("id_mode_numberCells_e__numbottle".getBytes());

						} else if (nextmode.equals("2") && mode.equals("2"))
						{
							//PLC工作结束传代模式到达输入传代瓶数的阶段,页面响应
							System.out.println("叫我输入细胞所需数量");
							break;
							//确定之后发送
							//调用 outputStream.write("id_mode_numberCells_e".getBytes());

						} else if (nextmode.equals("3") && mode.equals("2"))
						{
							//PLC工作结束传代模式到Remasks
							System.out.println("叫我输入细胞Remasks");
							break;
							//确定之后发送
							//调用 outputStream.write("id_Toid_BottleRemask".getBytes());\

						} else if (nextmode.equals("4") && mode.equals("2"))
						{
							//PLC工作结束传代模式到结束
							System.out.println("结束传代");
							break;

						} else if (nextmode.equals("1") && mode.equals("3"))
						{
							//PLC工作结束换液模式结束,页面响应
							System.out.println("换液结束");
							break;
						} else if (nextmode.equals("1") && mode.equals("4"))
						{
							//PLC工作结束瓶子销售结束,页面响应
							System.out.println("卖培养瓶结束");
							break;

						} else if (nextmode.equals("1") && mode.equals("5"))
						{
							//PLC工作结束管子开始消化,页面响应
							System.out.println("到显微镜下面观察是否已经完全消化");
							break;
						} else if (nextmode.equals("2") && mode.equals("5"))
						{
							//PLC工作结束管子销售结束,页面响应
							//调用 outputStream.write("id_Mode".getBytes());
							System.out.println("观察结束，拿冷冻管");
							break;

						}

					}
				}
				dos.close();
				socket.close();
			}
		} catch (Exception s)
		{
			System.out.println(s);
		}
	}
}
