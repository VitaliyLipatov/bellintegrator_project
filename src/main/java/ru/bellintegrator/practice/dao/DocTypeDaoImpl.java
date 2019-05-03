package ru.bellintegrator.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.model.DocType;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class DocTypeDaoImpl implements DocTypeDao {

    private final EntityManager em;

    /**
     * Конструктор
     *
     * @param em контекст
     */
    @Autowired
    public DocTypeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocType> list() {
        TypedQuery<DocType> query = em.createQuery("SELECT d FROM Doc_Type d", DocType.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DocType getByCode(String code) {
        TypedQuery<DocType> query = em.createQuery("SELECT d FROM Doc_Type d WHERE d.code = :code", DocType.class);
        query.setParameter("code", code);
        DocType docType;
        try {
            docType = query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return docType;
    }
}
