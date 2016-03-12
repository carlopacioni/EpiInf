/*
 * Copyright (C) 2015 Tim Vaughan <tgvaughan@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package epiinf;

/**
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public abstract class Event implements Comparable<Event> {
    public double time;
    public boolean isFinal = false;

    @Override
    public int compareTo(Event o) {
        if (this.time < o.time)
            return -1;
        else if (this.time > o.time)
            return 1;

        return 0;
    }
}
