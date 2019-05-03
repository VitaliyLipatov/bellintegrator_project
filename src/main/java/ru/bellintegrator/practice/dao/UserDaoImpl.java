package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    /**
     * Конструктор
     *
     * @param em контекст
     */
    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> list(User filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        Predicate predicate = criteriaBuilder.equal(userRoot.get("office").get("id"), filter.getOffice().getId());
        if (filter.getFirstName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userRoot.get("firstName"), "%" + filter.getFirstName() + "%"));
        }
        if (filter.getSecondName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userRoot.get("secondName"), "%" + filter.getSecondName() + "%"));
        }
        if (filter.getMiddleName() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userRoot.get("middleName"), "%" + filter.getMiddleName() + "%"));
        }
        if (filter.getPosition() != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(userRoot.get("position"), "%" + filter.getPosition() + "%"));
        }
        if (filter.getDoc().getDocType() != null) {
            predicate = criteriaBuilder.equal(userRoot.get("doc").get("docType"), filter.getDoc().getDocType());
        }
        if (filter.getCountry().getCode() != null) {
            predicate = criteriaBuilder.equal(userRoot.get("country").get("code"), filter.getCountry().getCode());
        }
        criteriaQuery.select(userRoot).where(predicate);
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(Long userId) {
        return em.find(User.class, userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(User updateUser) {
        User user = getById(updateUser.getId());
        user.setFirstName(updateUser.getFirstName());
        user.setSecondName(updateUser.getSecondName());
        user.setMiddleName(updateUser.getMiddleName());
        user.setPosition(updateUser.getPosition());
        user.setPhone(updateUser.getPhone());
        user.setDoc(updateUser.getDoc());
        user.getDoc().setNumber(updateUser.getDoc().getNumber());
        user.getDoc().setDate(updateUser.getDoc().getDate());
        user.getCountry().setCode(updateUser.getCountry().getCode());
        user.setIdentified(updateUser.getIdentified());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        em.persist(user);
    }
}
