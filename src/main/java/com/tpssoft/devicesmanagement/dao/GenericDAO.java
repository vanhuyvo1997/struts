package com.tpssoft.devicesmanagement.dao;

import java.util.Set;

public interface GenericDAO {
	public <T> void add(T t);

	public <E> void update(E id);

	public <E> void delete(E id);

	public <T, E> T getById(E id);

	public <T> Set<T> getAll();
}
