/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2012, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.cache.ehcache;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.boot.registry.selector.Availability;
import org.hibernate.boot.registry.selector.AvailabilityAnnouncer;
import org.hibernate.boot.registry.selector.SimpleAvailabilityImpl;
import org.hibernate.cache.spi.RegionFactory;

/**
 * Makes the 2 contained region factory implementations available to the Hibernate
 * {@link org.hibernate.boot.registry.selector.spi.StrategySelector} service.
 *
 * @author Steve Ebersole
 */
public class AvailabilityAnnouncerImpl implements AvailabilityAnnouncer {
	@Override
	@SuppressWarnings("unchecked")
	public Iterable<Availability> getAvailabilities() {
		final List<Availability> availabilities = new ArrayList<Availability>();

		availabilities.add(
				new SimpleAvailabilityImpl(
						RegionFactory.class,
						EhCacheRegionFactory.class,
						"ehcache",
						EhCacheRegionFactory.class.getSimpleName(),
						"org.hibernate.cache.EhCacheRegionFactory" // legacy impl class name
				)
		);

		availabilities.add(
				new SimpleAvailabilityImpl(
						RegionFactory.class,
						SingletonEhCacheRegionFactory.class,
						"ehcache-singleton",
						SingletonEhCacheRegionFactory.class.getSimpleName(),
						"org.hibernate.cache.SingletonEhCacheRegionFactory" // legacy impl class name
				)
		);

		return availabilities;
	}
}
