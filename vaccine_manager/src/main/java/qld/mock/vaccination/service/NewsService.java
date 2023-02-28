package qld.mock.vaccination.service;

import org.springframework.data.domain.Page;


import qld.mock.vaccination.entities.News;
import qld.mock.vaccination.entities.Schedule;


public interface NewsService {

	Page<News> getListNews(Integer pageNo, Integer pageSize);
	
	boolean createNews(News news);
	
	boolean updateNews(News news, Integer id);
	
	void deleteNewsById(News news, Integer id);
	
	Page<News> getListNewsByKeyWord(Integer pageNo, Integer pageSize, String keyWord);
	
	News getById(Integer id);
	
}
