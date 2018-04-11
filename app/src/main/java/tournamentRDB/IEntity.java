package tournamentRDB;

/**
 * Created by VSharon on 3/20/2018.
 */

public interface IEntity<T> {

    public void Add(T obj);

    public T Retrieve(String id);

    public void Update(T obj);

    public void Delete(String id);
}
