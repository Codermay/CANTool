package test;

import static org.junit.Assert.*;
import gnu.io.SerialPort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import serialPort.SerialTool;
import static org.mockito.Mockito.*;  
import CANTool.CANTool;

public class CANToolTest {
	private CANTool tool,spy;
	private SerialPort serialPort;
	private SerialTool serialTool;
	@Before
	public void setUp() throws Exception {
		serialPort=mock(SerialPort.class);
		tool = new CANTool(serialPort);
		spy = spy(tool);
		
	}

	@After
	public void tearDown() throws Exception {
	}
	//���Է��ذ汾��Ϣ
	@Test
	public void readCommandTest1() {
		
		doNothing().when(spy).returnTheInfo(1,"SV2.5-HV2.0");
		spy.readCommand("V\r");
		verify(spy,times(1)).returnTheInfo(1,"SV2.5-HV2.0");
		
	}
	
	//������������
	@Test
	public void readCommandTest2() {
		
		doNothing().when(spy).returnTheInfo(1,"");
		spy.readCommand("O1\r");
		verify(spy,times(1)).open();
		verify(spy,times(1)).returnTheInfo(1,"");
		
	}
	
	//���Է���������
	@Test
	public void readCommandTest3() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("O1\r");
		spy.readCommand("O1\r");
		verify(spy,times(2)).open();
		verify(spy,times(1)).returnTheInfo(1,"");
		verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	//���������ػ�
	@Test
	public void readCommandTest4() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("O1r");
		spy.readCommand("C\r");
		verify(spy,times(1)).open();
		verify(spy,times(1)).close();
		verify(spy,times(2)).returnTheInfo(1,"");
		
	}
	
	//���Է������ػ�
	@Test
	public void readCommandTest5() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("C\r");
		verify(spy,times(1)).close();
		verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	//���Թػ�״̬�µ����ٶ�
	@Test
	public void readCommandTest6() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("S1\r");
		verify(spy,times(1)).changeSpeed('1');
		verify(spy,times(1)).returnTheInfo(1,"");
		
	}
	
	//���Թػ�״̬�µ����ٶ�
	@Test
	public void readCommandTest7() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("O1r");
		spy.readCommand("S1\r");
		verify(spy,times(1)).changeSpeed('1');
		verify(spy,times(1)).returnTheInfo(1,"");
		verify(spy,times(1)).returnTheInfo(0,"");
		
	}

	//�������������ٶ�
	@Test
	public void readCommandTest8() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("S1\r");
		spy.readCommand("S2\r");
		spy.readCommand("S3\r");
		verify(spy,times(1)).changeSpeed('1');
		verify(spy,times(1)).changeSpeed('2');
		verify(spy,times(1)).changeSpeed('3');
		verify(spy,times(3)).returnTheInfo(1,"");
		
	}
	
	//���Է���1�α�׼֡
	@Test
	public void readCommandTest9() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("O1\r");
		spy.readCommand("t358800000000061030400000\r");
		verify(spy,times(1)).open();
		verify(spy,times(1)).sendStandardFrame("t358800000000061030400000\r");
		verify(spy,times(2)).returnTheInfo(1,"");
		//verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	//���Է��Ͷ�α�׼֡
	@Test
	public void readCommandTest10() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("O1\r");
		spy.readCommand("t358800301513034014880010\r");
		verify(spy,times(1)).open();
		verify(spy,times(1)).sendStandardFrame("t358800301513034014880010\r");
		verify(spy,times(2)).returnTheInfo(1,"");
		//verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	//���Է��ͱ�׼֡����
	@Test
	public void readCommandTest11() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("O1\r");
		spy.readCommand("t359800301513034014880010\r");
		verify(spy,times(1)).open();
		verify(spy,times(1)).sendStandardFrame("t359800301513034014880010\r");
		verify(spy,times(1)).returnTheInfo(1,"");
		verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	//����δ�������ͱ�׼֡
	@Test
	public void readCommandTest12() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		//spy.readCommand("O1\r");
		spy.readCommand("t359800301513034014880010\r");
		//verify(spy,times(1)).returnTheInfo(1,"");
		verify(spy,times(1)).sendStandardFrame("t359800301513034014880010\r");
		verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	//���Է���1����չ֡
	@Test
	public void readCommandTest13() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("O1\r");
		spy.readCommand("T00000358800000000061030400000\r");
		verify(spy,times(1)).open();
		verify(spy,times(1)).sendExtendedFrame("T00000358800000000061030400000\r");
		verify(spy,times(2)).returnTheInfo(1,"");
		//verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	//���Է��Ͷ����չ֡
	@Test
	public void readCommandTest14() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("O1\r");
		spy.readCommand("T00000358800301513034014880010\r");
		verify(spy,times(1)).open();
		verify(spy,times(1)).sendExtendedFrame("T00000358800301513034014880010\r");
		verify(spy,times(2)).returnTheInfo(1,"");
		//verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	//���Է�����չ֡����
	@Test
	public void readCommandTest15() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		spy.readCommand("O1\r");
		spy.readCommand("T00000359800301513034014880010\r");
		verify(spy,times(1)).open();
		verify(spy,times(1)).sendExtendedFrame("T00000359800301513034014880010\r");
		verify(spy,times(1)).returnTheInfo(1,"");
		verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	//����δ����������չ֡����
	@Test
	public void readCommandTest16() {
		
		doNothing().when(spy).returnTheInfo(anyInt(),anyString());
		//spy.readCommand("O1\r");
		spy.readCommand("T00000359800301513034014880010\r");
		verify(spy,times(1)).sendExtendedFrame("T00000359800301513034014880010\r");
		//verify(spy,times(1)).returnTheInfo(1,"");
		verify(spy,times(1)).returnTheInfo(0,"");
		
	}
	
	

}
