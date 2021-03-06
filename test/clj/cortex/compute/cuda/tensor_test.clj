(ns ^:gpu cortex.compute.cuda.tensor-test
  (:require [cortex.verify.tensor :as verify-tensor]
            [cortex.compute.verify.utils
             :refer [def-double-float-test
                     def-all-dtype-test
                     def-cas-dtype-test
                     *datatype*
                     test-wrapper]]
            [clojure.test :refer :all]))


(use-fixtures :each test-wrapper)

(defn create-driver
  []
  (require '[cortex.compute.cuda.driver :as cuda-driver])
  (require '[cortex.compute.cuda.tensor-math])
  ((resolve 'cuda-driver/driver)))


(def-all-dtype-test assign-constant!
  (verify-tensor/assign-constant! (create-driver) *datatype*))


(def-all-dtype-test assign-marshal
  (verify-tensor/assign-marshal (create-driver) *datatype*))


(def-cas-dtype-test unary-op
  (verify-tensor/unary-op (create-driver) :int))


(def-cas-dtype-test binary-constant-op
  (verify-tensor/binary-constant-op (create-driver) *datatype*))


(def-cas-dtype-test binary-op
  (verify-tensor/binary-op (create-driver) *datatype*))


(def-double-float-test gemm
  (verify-tensor/gemm (create-driver) *datatype*))


(def-double-float-test gemv
  (verify-tensor/gemv (create-driver) *datatype*))


(def-double-float-test batch-normalize
  (verify-tensor/batch-normalize (create-driver) *datatype*))


(def-double-float-test batch-normalize-update-and-apply
  (verify-tensor/batch-normalize-update-and-apply (create-driver) *datatype*))


(def-double-float-test batch-normalize-gradients
  (verify-tensor/batch-normalize-gradients (create-driver) *datatype*))


(def-double-float-test activation-forward
  (verify-tensor/activation-forward (create-driver) *datatype*))


(def-double-float-test activation-gradient
  (verify-tensor/activation-gradient (create-driver) *datatype*))
