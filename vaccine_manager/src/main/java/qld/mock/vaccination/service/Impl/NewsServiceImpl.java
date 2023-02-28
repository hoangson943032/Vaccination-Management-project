package qld.mock.vaccination.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import qld.mock.vaccination.Exception.NotFountException;
import qld.mock.vaccination.entities.News;
import qld.mock.vaccination.entities.Schedule;
import qld.mock.vaccination.repositories.NewsRepositories;
import qld.mock.vaccination.service.NewsService;


@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsRepositories newsRepositories;
	
	
	@Override
	public Page<News> getListNews(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return newsRepositories.findAll(pageable);
	}

	@Override
	public Page<News> getListNewsByKeyWord(Integer pageNo, Integer pageSize, String keyWord) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		List<News> news = newsRepositories.Search(keyWord);
		int total = news.size();
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), total);

		List<News> output = new ArrayList<>();

		if (start <= end) {
		    output = news.subList(start, end);
		}

		return new PageImpl<>(
		    output,
		    pageable,
		    total
		);
	
	}
	
	@Override
	public boolean createNews(News news) {
		
		
			return newsRepositories.save(news) != null;

	}

	@Override
	public boolean updateNews(News news, Integer id) {
		News data = newsRepositories.findById(id).get();
		if(data != null) {
			data.setContent(news.getContent());
			data.setPreview(news.getPreview());
			data.setTitle(news.getTitle());
			newsRepositories.save(data);
			return true;
		}
		return false;
	}

	@Override
	public void deleteNewsById(News news, Integer id) {
		News dlNews = newsRepositories.findById(id).get();
		newsRepositories.delete(dlNews);
	}

	@Override
	public News getById(Integer id) {
		
		return newsRepositories.findById(id).get();
	}

	
}
