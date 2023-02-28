package qld.mock.vaccination.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import qld.mock.vaccination.entities.News;



@Repository
public interface NewsRepositories extends JpaRepository<News, Integer> {

	
	@Query("SELECT n FROM News n WHERE CONCAT(n.content, ' ', n.preview, ' ', n.title) LIKE %?1%")
	public List<News> Search(String keyWord);
}
