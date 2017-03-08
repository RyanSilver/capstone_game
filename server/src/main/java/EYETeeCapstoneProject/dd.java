package EYETeeCapstoneProject;

import java.lang.*;
//import java.lang.reflect.Method;

class aclass extends Thread
{
	public aclass()
	{
		super();
		start();
	}
	public void run()
	{
	Thread t = Thread.currentThread();
	String name = t.getName();
    	System.out.println("CRAPPER-A:" + name);
	}
}

class bclass extends Thread
{
	public bclass()
	{
		super();
		start();
	}
	public void run()
	{
	Thread t = Thread.currentThread();
	String name = t.getName();
    	System.out.println("CRAPPER-B:" + name);
	}
}

public class dd
{
	/*
	public static void main(String[] args)
	{
		Object b = null;
		try {
			Class cc = null;
			if (args[0].equals("a"))
				b = new aclass();
			else
				b = new bclass();
			//cc = Class.forName("aclass.class");
			//aclass aa = (aclass) new cc.newInstance();
		}
		catch(Exception e) {e.printStackTrace();}

		//aclass c = (aclass) b;

		System.out.println(b.getClass());
	}
	*/
}
