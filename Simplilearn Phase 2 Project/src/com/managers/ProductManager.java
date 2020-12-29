package com.managers;

import java.util.List;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.pojos.ProductToken;

public class ProductManager 
{
	private static SessionFactory factory;
	
	public boolean addProduct(String productName, int price)
	{
		try
		{
			factory = new Configuration().configure().buildSessionFactory();
		}
		catch (Exception e)
		{
			System.out.println("Failed to initialize session factory: " + e);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;
		boolean presentFlag = false;
		try
		{
			tx = session.beginTransaction();
			List checkVals = session.createQuery("FROM PRODUCTS WHERE PRODUCTS.product_name = " + productName).list();
			if (checkVals.isEmpty())
			{
				ProductToken product = new ProductToken(productName, price);
				session.save(product);
				tx.commit();
			}
			else
			{
				presentFlag = true;
			}
		}
		catch (HibernateException e)
		{
			System.out.println("Fault in addProduct: " + e);
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
	
	public boolean listProducts(String productName, int price)
	{
		try
		{
			factory = new Configuration().configure().buildSessionFactory();
		}
		catch (Exception e)
		{
			System.out.println("Failed to initialize session factory: " + e);
		}
		
		Session session = factory.openSession();
		Transaction tx = null;
		boolean validFlag = true;
		try
		{
			tx = session.beginTransaction();
			List products = session.createQuery("FROM PRODUCTS").list();
			if (products.isEmpty())
			{
				validFlag = false;
			}
		}
		catch (HibernateException e)
		{
			System.out.println("Fault in checkProduct: " + e);
			if (tx != null)
			{
				tx.rollback();
			}
		}
		finally
		{
			session.close();
		}
		return validFlag;
	}
}