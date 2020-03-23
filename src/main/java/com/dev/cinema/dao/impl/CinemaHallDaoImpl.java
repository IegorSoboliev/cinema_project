package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.CinemHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl implements CinemHallDao {
    private SessionFactory sessionFactory;

    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(cinemaHall);
            transaction.commit();
            cinemaHall.setId(id);
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Cannot add cinema hall to database", e);
        }
    }

    @Override
    public List<CinemaHall> getAll() throws DataProcessingException {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<CinemaHall> criteriaQuery =
                    session.getCriteriaBuilder().createQuery(CinemaHall.class);
            criteriaQuery.from(CinemaHall.class);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot show all cinema halls from database", e);
        }
    }

    @Override
    public CinemaHall getById(Long cinemaHallId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CinemaHall.class, cinemaHallId);
        } catch (Exception e) {
            throw new DataProcessingException("Cannot show cinema hall from database", e);
        }
    }
}
