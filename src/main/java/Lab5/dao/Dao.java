package Lab5.dao;

import Lab5.exceptions.CharacterDaoException;
import Lab5.exceptions.WeaponDaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Optional<T> findById(Long id) throws WeaponDaoException, CharacterDaoException;
    List<T> findAll() throws WeaponDaoException, CharacterDaoException;
    Long save(T t) throws WeaponDaoException, CharacterDaoException;
    void update(T t) throws WeaponDaoException, CharacterDaoException;
    void delete(Long id) throws WeaponDaoException, CharacterDaoException;
    //void update(T t, String[]params);
}
