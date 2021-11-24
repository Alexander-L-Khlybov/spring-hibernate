package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private String qs =
            "SELECT u " +
                    "FROM User u INNER JOIN u.car c " +
                    "WHERE c.model = :model and c.series = :series";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User").getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {

        return (User) sessionFactory.openSession()
                .createQuery(qs)
                .setParameter("model", model)
                .setParameter("series", series)
                .list()
                .get(0);
    }
}
