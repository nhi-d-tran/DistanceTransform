(defun MULTIPLE-MEMBER
	(X L)
	(if (and (or (symbolp x) (numberp X)) (not (endp L))) 
		(if (eq (car L) X)
			(member X (cdr L))
			(MULTIPLE-MEMBER X (cdr L)))))

(defun same-sign
	(a b)
	(and (numberp a) (numberp b)
		(or (and (zerop a) (zerop b)) (and (< a 0) (< b 0)) (and (> a 0) (> b 0)))))


(defun safe-div
	(a b)
	(and (numberp a) (numberp b) (not (zerop b)) (/ a b)))


e/ lab 4
(defun my-isort (l)
	(if (endp l) 
		()
		(let ((x (isort (cdr L))))
			(insert (car L) X))))
	
f/ lab 4
(defun my-split-list (L)
	(if (endp L)
		'(()())
		(let ((X (split-list (cdr L))))
			(list (cons (car L) (cadr X)) (car X)))))

(defun set-remove (X S)
	(cond ((endp S) ())
		  ((equal (car S) X) (set-remove x (cdr s)))
		  (t (cons (car S) (set-remove x (cdr s))))))


(defun set-excl-union (s1 s2)
	(cond ((endp s1) s2)
			((member (car s1) s2) (set-remove (car s1) (set-excl-union (cdr s1) s2)))
			(t (cons (car s1) (set-excl-union (cdr s1) s2)))))

(defun singletons (e)
	(cond ((endp e) ())
		((member (car e) (cdr e)) (set-remove (car e) (singletons (cdr e))))
		(t (cons (car e) (singletons (cdr e))))))


(defun min-first (L)
     (if (endp (cdr L))
         L
         (let ((X (min-first (cdr L))))
             (if (<= (car L) (car X))
                 L
                 (cons (car X) (cons (car L) (cdr X)))))))

(defun ssort (L)
	(cond ((endp L) ())
		(t (let ((X (min-first L)))
			(cons (car X) (ssort (cdr X)))))))

(defun qsort (L)
	(if (endp L) 
		()
		(let ((PL (partition L (car L))))
			(if (endp (car PL))
				(cons (car L) (qsort (cdr L)))
				(append (qsort (car PL)) (qsort (cadr PL)))))))

(defun merge-lists (l1 l2)
	(cond ((endp l1) l2)
		((endp l2) l1)
		((< (car l1) (car l2)) (cons (car l1) (merge-lists (cdr l1) l2)))
		(t (cons (car l2) (merge-lists l1 (cdr l2))))))

(defun msort (L)
     (cond ((endp L) ())
         ((endp (cdr L)) L)
         (t (let ((X (split-list L)))
            (merge-lists (msort (car X)) (msort (cadr X)))))))

; a b a a a c c

(defun unrepeated-elts (L)
     (cond ((endp L) ())
         ((or (endp (cdr L)) (not (equal (car L) (cadr L)))) (cons (car L) (unrepeated-elts (cdr L))))
         ((or (endp (cddr L)) (not (equal (car L) (caddr L)))) (unrepeated-elts (cddr L)))
         (t (unrepeated-elts (cdr L)))))
		 
;Question 9
(defun repeated-elts (L)
     (cond ((endp L) ())
         ((or (endp (cdr L)) (not (equal (car L) (cadr L)))) (repeated-elts (cdr L)))
         ((or (endp (cddr L)) (not (equal (car L) (caddr L)))) (cons (car L) (repeated-elts (cddr L))))
         (t (repeated-elts (cdr L)))))

(defun count-repetitions (L)
     (cond ((endp L) ())
         ((endp (cdr L)) (list (cons 1 L)))
         (t (let ((X (count-repetitions (cdr L))))
             (if (not (equal (car L) (cadr L)))
                 (cons (list 1 (car L)) X)
                 (cons (list (+ 1 (caar X)) (car L)) (cdr X)))))))


(defun PARTITION1 (f L p)
     (if (endp L)
         (list nil nil)
         (let ((x (partition1 f (cdr L) p)))
		     (cond ((funcall f (car L) p)
		         (append (list (cons (car L) (car x))) (list (cadr x))))
		         (t (append (list (car x)) (list (cons (car L) (cadr x)))))))))	

(defun QSORT1 (f L)
     (cond ((endp L) nil)
	     (t (let ((Z (partition1 f L (car L))))  
	             (cond ((endp (car Z)) (cons (car L) (qsort1 f (cdr L))))
		             (t (let ((x (qsort1 f (car Z)))
		                 (y (qsort1 f (cadr Z)))) 
			             (append  x  y))))))))


(defun FOO (f L)
     (if (endp L) 
         () 
         (let* ((x (foo f (cdr L)))
		 	 (ls (cdr L))
	         (Z (list (cons (funcall f (car L)) ls)))
	         (Y (mapcar (lambda (a) (cons (car L) a)) x)))
	         (append Z Y))))




(setf e '((-1 2) ((90 91) 92 93 94) (9 19 29 39 49)))




















