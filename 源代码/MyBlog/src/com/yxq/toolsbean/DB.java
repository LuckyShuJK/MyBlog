package com.yxq.toolsbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private final String url = "jdbc:mysql://localhost:3306/db_blog?useUnicode=true&characterEncoding=utf-8";
    private final String userName = "root";
    private final String password = "123456";
    private Connection con = null;
    private Statement stm=null;
    
    /* ͨ�����췽���������ݿ����� */
    public DB(){
    	try {   		
    		//1����
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("�������ݿ������ɹ���");
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("�������ݿ�����ʧ�ܣ�");
    	}    	
    }
    /* �������ݿ����� */
    public void createCon() {
        try {
        	//2����������
            con = DriverManager.getConnection(url, userName, password);
            System.out.println("��ȡ���ݿ����ӳɹ���");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("��ȡ���ݿ�����ʧ�ܣ�");
        }
    }
    /* ��ȡStatement���� */
    public void getStm(){
   		createCon();
    	try {
    		//3������Statement����
			stm=con.createStatement();
			System.out.println("����Statement����ɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("����Statement����ʧ�ܣ�");
		}
    }
    /** 
     * @���� �����ݿ�����ӡ��޸ĺ�ɾ���Ĳ���
     * @���� sqlΪҪִ�е�SQL���
     * @����ֵ boolean��ֵ 
     */
    public boolean executeUpdate(String sql) {
    	//System.out.println(sql);
        boolean mark=false;
        getStm();
    	try {   	
    		//4ִ�в���
            int iCount = stm.executeUpdate(sql);
            if(iCount>0)            	
            	mark=true; 
            else
            	mark=false;
        } catch (Exception e) {
            e.printStackTrace();
		    mark=false;
        }
        return mark;
    }
    /* ��ѯ���ݿ� */
    public ResultSet executeQuery(String sql) {
        ResultSet rs=null;      
        getStm();
        try {
                rs = stm.executeQuery(sql);
            } 
        catch (Exception e) {
            	e.printStackTrace();
                System.out.println("��ѯ���ݿ�ʧ�ܣ�");
            }       
        return rs;
    }
    /* �ر����ݿ�Ĳ��� */
    public void closed() {
    	if(stm!=null)
			try {
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�stm����ʧ�ܣ�");
			}
    	if(con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("�ر�con����ʧ�ܣ�");
			}
    }
    public static void main(String[] args){
		DB d=new DB();
		boolean m=d.executeUpdate("INSERT INTO tb_articleType VALUES(5,'ɢ��','����ɢ��')");
	    System.out.print(m);
	    boolean n=d.executeUpdate("update tb_articleType set articleType_info='����ɢ��' where articleType_id=5");
	    System.out.print(n);
	    //ɾ��
	    //��ѯ
	    //d.closed();
	    
    }
}
