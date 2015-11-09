/*
 * Copyright (C) 2013 Tim Vaughan <tgvaughan@gmail.com>
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

package epiinf.models;

import beast.core.Function;
import beast.core.Input;
import beast.core.Input.Validate;
import beast.core.parameter.IntegerParameter;
import beast.core.parameter.RealParameter;
import epiinf.EpidemicEvent;
import epiinf.EpidemicState;

/**
 * General stochastic SIR model of an epidemic.
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class SIRModel extends EpidemicModel {
    
    public Input<IntegerParameter> S0Input = new Input<>(
            "S0", "Initial size of susceptible population.", Validate.REQUIRED);
    
    public Input<Function> infectionRateInput = new Input<>(
            "infectionRate", "Infection rate.", Validate.REQUIRED);

    public Input<RealParameter> infectionRateShiftTimesInput = new Input<>(
            "infectionRateShiftTimes", "Infection rate shift times.");

    public Input<Function> recoveryRateInput = new Input<>(
            "recoveryRate", "Recovery rate.", Validate.REQUIRED);

    public Input<RealParameter> recoveryRateShiftTimesInput = new Input<>(
            "recoveryRateShiftTimes", "Recovery rate shift times.");

    @Override
    public Function getInfectionRateParam() {
        return infectionRateInput.get();
    }

    @Override
    public RealParameter getInfectionRateShiftTimesParam() {
        return infectionRateShiftTimesInput.get();
    }

    @Override
    public Function getRecoveryRateParam() {
        return recoveryRateInput.get();
    }

    @Override
    public RealParameter getRecoveryRateShiftTimesParam() {
        return recoveryRateShiftTimesInput.get();
    }

    @Override
    public EpidemicState getInitialState() {
        return new EpidemicState(S0Input.get().getValue(), 1, 0);
    }

    @Override
    protected double calculateInfectionPropensity(EpidemicState state) {
        return rateCache.get(state.intervalIdx)[EpidemicEvent.INFECTION]*state.S*state.I;
    }

    @Override
    protected double calculateRecoveryPropensity(EpidemicState state) {
        return rateCache.get(state.intervalIdx)[EpidemicEvent.RECOVERY]*state.I;
    }

    @Override
    public void incrementState(EpidemicState state, EpidemicEvent event) {
        switch(event.type) {
            case EpidemicEvent.INFECTION:
                state.S -= event.multiplicity;
                state.I += event.multiplicity;
                break;
            case EpidemicEvent.RECOVERY:
            case EpidemicEvent.RHO_SAMPLE:
            case EpidemicEvent.PSI_SAMPLE_REMOVE:
            case EpidemicEvent.OTHER_SAMPLE:
                state.I -= event.multiplicity;
                state.R += event.multiplicity;
                break;
            default:
                break;
        }
    }
}
