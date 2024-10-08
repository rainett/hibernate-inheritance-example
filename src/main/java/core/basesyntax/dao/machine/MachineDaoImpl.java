package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        return super.saveEntity(machine);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Machine "
                            + "where (year(current_date) - year) > :age", Machine.class)
                    .setParameter("age", age)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to find machines by age older than = ["
                    + age + "]", e);
        }
    }
}
