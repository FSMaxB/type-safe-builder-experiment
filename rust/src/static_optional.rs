use std::marker::PhantomData;

pub trait StaticOptional {
	type Element;

	fn some(element: Self::Element) -> StaticSome<Self::Element> {
		StaticSome(element)
	}

	fn none() -> StaticNone<Self::Element> {
		StaticNone::default()
	}
}

pub struct StaticSome<Element>(pub Element);

impl<Element> StaticOptional for StaticSome<Element> {
	type Element = Element;
}

impl<Element> StaticOptional for StaticNone<Element> {
	type Element = Element;
}

pub struct StaticNone<Element> {
	_element: PhantomData<Element>,
}

impl<Element> Default for StaticNone<Element> {
	fn default() -> Self {
		Self {
			_element: Default::default(),
		}
	}
}
