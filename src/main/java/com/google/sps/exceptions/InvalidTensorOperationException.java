package com.google.sps.exceptions;

import com.google.sps.proto.SimulationTraceProto.*;


public class InvalidTensorOperationException extends Exception {
    private int traceAddress;
    private int allocatedTensor;
    private int traceTensor;
    private int instruction; // Tag of instruction performing the operation
    private String memoryAccess; // String representing the access type
  
    public InvalidTensorOperationException(int traceAddress, int allocatedTensor, int traceTensor, int instruction, TraceEntry.AccessType memoryAccess) {
      this.traceAddress = traceAddress;
      this.allocatedTensor = allocatedTensor;
      this.traceTensor = traceTensor;
      this.instruction = instruction;
      this.memoryAccess = memoryAccess.getDescriptorForType().getName();
    }
  
    /** Outputs information related to the exception. */
    @Override
    public String getMessage() {
      String returnString;
      if (allocatedTensor == -1) {
        returnString = "No tensor allocation found at address "
          + traceAddress
          + " while validating a "
          + memoryAccess
          + " on tensor "
          + traceTensor
          + " for instruction "
          + instruction
          + ".";
      }
      else {
        returnString = memoryAccess 
          + " on tensor "
          + traceTensor
          + " at address "
          + traceAddress
          + " invald, tensor allocated at address "
          + traceAddress
          + " is tensor "
          + allocatedTensor
          + ".";
      }
      
      return returnString;
    }
}