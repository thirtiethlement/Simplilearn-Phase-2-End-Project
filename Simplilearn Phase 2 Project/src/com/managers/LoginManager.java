package com.managers;

import java.util.List;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.pojos.LoginToken;
import com.util.HibernateUtil;

public class LoginManager 
{
	
	@SuppressWarnings("unchecked")
	public boolean addLogin(String username, String password)
	{
		
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		boolean presentFlag = false;
		try
		{
			tx = session.beginTransaction();
			List<LoginToken> checkVals = session.createQuery("FROM LoginToken a WHERE a.username = '" + username + "'").list();
			if (checkVals.isEmpty())
			{
				LoginToken login = new LoginToken(username, password);
				session.save(login);
				tx.commit();
			}
			else
			{
				presentFlag = true;
			}
		}
		catch (HibernateException e)
		{
			System.out.println("Fault in addLogin: " + e);
			if (tx != null)
			{
				tx.rollback();
			}
		}
		finally
		{
			session.close();
		}
		return presentFlag;
	}
	
	@SuppressWarnings("unchecked")
	public boolean checkLogin(String username, String password)
	{
		
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		boolean validFlag = true;
		try
		{
			tx = session.beginTransaction();
			List<LoginToken> checkVals = session.createQuery("FROM LoginToken a WHERE a.username = '" + username + "' AND a.password = '" + password + "'").list();
			if (checkVals.isEmpty())
			{
				validFlag = false;
			}
		}
		catch (HibernateException e)
		{
			System.out.println("Fault in checkLogin: " + e);
			if (tx != null)
			{
				tx.rollback();
			}
			e.printStackTrace();
		}
		finally
		{
			session.close();
		}
		return validFlag;
	}
}
