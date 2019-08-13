package br.com.ramon.gra;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import br.com.ramon.gra.model.Movie;
import br.com.ramon.gra.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GraApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private MovieRepository movieRep;

	private CSVParser parse;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {

		try {
			insertDataFromCsv();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	private void insertDataFromCsv() throws IOException {

		InputStream inputData = new ClassPathResource("movielist.csv").getInputStream();
		parse = new CSVParser(new InputStreamReader(inputData), CSVFormat.newFormat(';').withFirstRecordAsHeader());
		parse.getRecords().forEach(m -> {

			Integer year = Integer.valueOf(m.get(0));
			String title = m.get(1).trim();
			String[] studios = m.get(2).split(",| and ");
			String[] producers = m.get(3).split(",| and ");
			Boolean winner = new Boolean(m.get(4).trim().equalsIgnoreCase("yes"));

			saveMovie(year, title, studios, producers, winner);
		});

	}

	private void saveMovie(Integer year, String title, String[] studios, String[] producers, Boolean winner) {
		Movie movie = new Movie();

		movie.setYear(year);
		movie.setTitle(title);
		movie.setStudio(Arrays.deepToString(studios));
		movie.setProducers(Arrays.deepToString(producers));
		movie.setWinner(winner);

		movieRep.save(movie);
	}
}