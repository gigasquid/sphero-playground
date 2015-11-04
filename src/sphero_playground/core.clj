(ns sphero-playground.core
  (:require
   [ellipso.core :as core]
   [ellipso.sensor-data :as sensors]
   [ellipso.commands :as commands]))

;;;  On a mac go to your bluetooth
;;;  Next, hit your sphero so it lights up
;;;  Wait until the computer offers to pair with it and click pair
;;; It should say "connected" ... then quickly
;;  Verify what tty it is by doing `ls /dev/tty.Sphero*`
;;  then evaluating the following:

(def sphero (core/connect "/dev/tty.Sphero-YRR-AMP-SPP"))

;; Now you can try changing its color

(def RED 0xFF0000)
(def YELLOW 0xFF8000)
(def BLUE 0x0000FF)
(def PURPLE 0xFF00FF)

(commands/execute sphero (commands/colour RED))
(commands/execute sphero (commands/colour YELOW))
(commands/execute sphero (commands/colour BLUE))
(commands/execute sphero (commands/colour PURPLE))

;; Notice that the color is hex - so there are lots more possibilites

;; Now let's try to roll

;; This sets the sphero's heading
;http://sdk.sphero.com/sphero-robot-basics/heading-and-aiming/
(commands/execute sphero (commands/heading 0))
;; Now it can roll - the first arg is the speed in hex, the second is
;the degrees
(commands/execute sphero (commands/roll 0x4B 180))

;;; Let's make a bit easier by making a function to convert int to hex
(defn hex [i]
  (read-string (.toUpperCase (format "0x%x" i))))

(commands/execute sphero (commands/roll (hex 75) 180))
(commands/execute sphero (commands/roll (hex 99) 0))


;;take a look at this https://github.com/mattdenner/ellipso to get
;;more ideas

;;; print out the sensor data
((sensors/data-streaming sensors/everything 100 println) sphero)


;;; Use this to disconnect when you are done
  (comment  (core/disconnect sphero))
