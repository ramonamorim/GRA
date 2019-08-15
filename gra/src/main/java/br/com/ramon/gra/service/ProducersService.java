package br.com.ramon.gra.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.ramon.gra.model.ProducerVO;
import br.com.ramon.gra.model.Movie;
import br.com.ramon.gra.model.ProducerMinMax;
import br.com.ramon.gra.repository.MovieRepository;

@Service
public class ProducersService {

	@Autowired
	private MovieRepository movieRepo;

	@Cacheable("producers")
	public ProducerVO getProducerResult() {
		HashMap<String, List<Integer>> map = new HashMap<>();
		ProducerVO producerResult = new ProducerVO();

		mapAllWinnerProducers(map);

		ProducerMinMax min = getProducerListMin(map);
		ProducerMinMax max = getProducerListMax(map);

		producerResult.setMin(Arrays.asList(min));
		producerResult.setMax(Arrays.asList(max));

		return producerResult;
	}

	private void mapAllWinnerProducers(HashMap<String, List<Integer>> map) {
		movieRepo.findByWinner(true).stream().forEach(m -> {

			removeBrackets(m);

			List<String> producersList = Arrays.asList(m.getProducers().split(",\\s*"));

			for (String producer : producersList) {
				List<Integer> years = map.get(producer);
				if (years == null) {
					years = new LinkedList<>();
					years.add(m.getYear());
					map.put(producer, years);
				} else {
					years.add(m.getYear());
					map.put(producer, years);
				}
			}
		});
	}

	private void removeBrackets(Movie m) {
		m.setProducers(m.getProducers().replace("[", ""));
		m.setProducers(m.getProducers().replace("]", ""));
	}

	private ProducerMinMax getProducerListMax(HashMap<String, List<Integer>> map) {
		List<ProducerMinMax> list = new ArrayList<>();

		map.entrySet().stream().filter(m -> m.getValue().size() > 1).forEach(i -> {

			ProducerMinMax minItem = null;
			List<Integer> years = i.getValue();
			Integer max = null;
			Integer previous = null;
			Integer next = null;
			Integer diff = null;

			for (int value = 0; value < (years.size() - 1); value++) {
				previous = years.get(value);
				next = years.get(value + 1);
				diff = next - previous;
				if (max == null) {
					max = diff;
				} else if (max < diff) {
					max = diff;
				}
			}

			minItem = new ProducerMinMax(i.getKey(), diff, previous, next);
			list.add(minItem);
		});

		ProducerMinMax max = list.stream().max(Comparator.comparing(ProducerMinMax::getInterval))
				.orElseThrow(NoSuchElementException::new);

		return max;
	}

	private ProducerMinMax getProducerListMin(HashMap<String, List<Integer>> map) {
		List<ProducerMinMax> list = new ArrayList<>();

		map.entrySet().stream().filter(m -> m.getValue().size() > 1).forEach(i -> {

			ProducerMinMax producerMin = null;
			List<Integer> years = i.getValue();

			Integer min = null;
			Integer previous = null;
			Integer next = null;
			Integer diff = null;

			for (int value = 0; value < (years.size() - 1); value++) {
				previous = years.get(value);
				next = years.get(value + 1);
				diff = next - previous;
				if (min == null) {
					min = diff;
				} else if (min > diff) {
					min = diff;
				}
			}

			producerMin = new ProducerMinMax(i.getKey(), diff, previous, next);
			list.add(producerMin);

		});

		ProducerMinMax min = list.stream().min(Comparator.comparing(ProducerMinMax::getInterval))
				.orElseThrow(NoSuchElementException::new);
		return min;
	}

}
