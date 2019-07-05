package com.tpssoft.devicesmanagement.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.tpssoft.devicesmanagement.model.SuperCategory;

public class SuperCategoryDAO {
	private SessionFactory factory;

	public SuperCategoryDAO() {
		super();
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {
		SuperCategoryDAO daoSupCate = new SuperCategoryDAO();
		Integer cateID1 = daoSupCate.addSuperCategory("Nguyen Van Teo");
		System.out.println(cateID1);

		daoSupCate.updateSuperCategory(cateID1, "Buoi Quang Sang");
		daoSupCate.getAllSuperCategory();
		List<SuperCategory> superCategories = daoSupCate.getAllSuperCategory();
		SuperCategory superCategory = daoSupCate.getSuperCategoryById(cateID1);
		System.out.println(superCategory.toString());
		for (Iterator<SuperCategory> iterator = superCategories.iterator(); iterator.hasNext();) {
			SuperCategory supCategory = iterator.next();
			System.out.print("First Name: " + supCategory.getIdsupercategory());
			System.out.print("  Last Name: " + supCategory.getName());
			System.out.println();
		}
		daoSupCate.deleteSuperCategory(cateID1);
	}

	/* Method to CREATE an employee in the database */
	public Integer addSuperCategory(String name) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idSuperCategory = null;
		try {
			tx = session.beginTransaction();
			SuperCategory superCategory = new SuperCategory();
			superCategory.setName(name);
			idSuperCategory = (Integer) session.save(superCategory);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return idSuperCategory;
	}

	@SuppressWarnings("unchecked")
	public List<SuperCategory> getAllSuperCategory() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<SuperCategory> superCategories = null;
		try {
			tx = session.beginTransaction();
			superCategories = session.createQuery("FROM SuperCategory").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return superCategories;
	}

	public SuperCategory getSuperCategoryById(Integer idSuperCategory) {
		Session session = factory.openSession();
		Transaction tx = null;
		SuperCategory superCategory = null;
		try {
			tx = session.beginTransaction();
			superCategory = session.get(SuperCategory.class, idSuperCategory);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return superCategory;
	}

	public boolean updateSuperCategory(Integer idSuperCategory, String name) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			tx = session.beginTransaction();
			SuperCategory superCategory = session.get(SuperCategory.class, idSuperCategory);
			superCategory.setName(name);
			session.update(superCategory);
			tx.commit();
			isSuccess = true;
		} catch (HibernateException e) {
			isSuccess = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isSuccess;
	}

	public boolean deleteSuperCategory(Integer idSuperCategory) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			tx = session.beginTransaction();
			SuperCategory superCategory = session.get(SuperCategory.class, idSuperCategory);
			session.delete(superCategory);
			tx.commit();
			isSuccess = true;
		} catch (HibernateException e) {
			isSuccess = false;
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isSuccess;
	}

}
