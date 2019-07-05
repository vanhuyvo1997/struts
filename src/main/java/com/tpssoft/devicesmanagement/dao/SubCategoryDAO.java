package com.tpssoft.devicesmanagement.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.tpssoft.devicesmanagement.model.SubCategory;
import com.tpssoft.devicesmanagement.model.SuperCategory;

public class SubCategoryDAO {
	private static SessionFactory factory;

	public SubCategoryDAO() {
		super();
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

//	public static void main(String[] args) {
//		SuperCategoryDAO superCateDAO = new SuperCategoryDAO();
//		SuperCategory superCate = superCateDAO.getSuperCategoryById(12);
//		SubCategoryDAO daoSubCate = new SubCategoryDAO();
//		System.out.println(daoSubCate.addSubCategory(superCate, "SubCategory"));
//		daoSubCate.updateSubCategory(superCate, 4, "Nguyen Trung Nhan");
//		List<SubCategory> subCategories = daoSubCate.getAllSubCategory();
//
////		List<SuperCategory> superCategories =daoSupCate.getAllSuperCategory();
////		SuperCategory superCategory = daoSupCate.getOneSuperCategory(cateID1);
////		System.out.println(superCategory.toString());
//		for (Iterator<SubCategory> iterator = subCategories.iterator(); iterator.hasNext();) {
//			SubCategory subCategory = (SubCategory) iterator.next();
//			System.out.print("First Name: " + subCategory.getIdsubcategory());
//			System.out.print("  Last Name: " + subCategory.getName());
//			System.out.println();
//		}
//		daoSubCate.deleteSubCategory(4);
////		daoSupCate.deleteSuperCategory(cateID1);
//	}

	/* Method to CREATE an employee in the database */
	public Integer addSubCategory(SuperCategory superCategory, String name) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer idSubCategory = null;
		try {
			tx = session.beginTransaction();
			SubCategory subCategory = new SubCategory();
			subCategory.setName(name);
			subCategory.setSupercategory(superCategory);
			idSubCategory = (Integer) session.save(subCategory);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return idSubCategory;
	}

	@SuppressWarnings("unchecked")
	public List<SubCategory> getAllSubCategory() {
		Session session = factory.openSession();
		Transaction tx = null;
		List<SubCategory> subCategories = null;
		try {
			tx = session.beginTransaction();
			subCategories = session.createQuery("FROM SubCategory").list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return subCategories;
	}

	public SuperCategory getOneSuperCategory(Integer idSuperCategory) {
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

	public boolean updateSubCategory(SuperCategory superCategory, Integer idSubCategory, String name) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			tx = session.beginTransaction();
			SubCategory subCategory = session.get(SubCategory.class, idSubCategory);
			subCategory.setName(name);
			subCategory.setSupercategory(superCategory);
			session.update(subCategory);
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

	public boolean deleteSubCategory(Integer idSubCategory) {
		Session session = factory.openSession();
		Transaction tx = null;
		boolean isSuccess = false;
		try {
			tx = session.beginTransaction();
			SubCategory subCategory = session.get(SubCategory.class, idSubCategory);
			session.delete(subCategory);
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
